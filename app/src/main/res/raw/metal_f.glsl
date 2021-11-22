//--------------------------------------------------------------------------------------
// The fragment shader
//--------------------------------------------------------------------------------------
precision mediump float;       	// Set the default precision to medium. We don't need as high of a
                                // precision in the fragment shader.
varying vec2 v_TexCoord;
varying vec4 v_Light;
varying vec3 v_View;

float Roughness = 10.0;
float Reflection = 10.0;       // Fresnel reflection index
float DiffuseIntensity = 1.0;
float SpecularIntensity = 1.0;
float UVPull = 1.0;
uniform sampler2D u_DiffuseTexture;
uniform sampler2D u_BumpTexture;


void main()
{
    // Using Cook-Torrance lighting model
    vec2 UV = v_TexCoord * vec2( 1.0, UVPull );
    vec4 color = texture2D( u_DiffuseTexture, UV );
    vec4 N = texture2D( u_BumpTexture, UV ) * 2.0 - 1.0;

    float m = Roughness;
    float RI = Reflection;
    const float PI = 3.1415926535;

    // Normalize per-pixel vectors
    vec3 L = normalize( v_Light.xyz );
    vec3 V = normalize( v_View );
    vec3 H = normalize( L + V );

    float NH = max( 0.0, dot( N.xyz, H ) );
    float VH = max( 0.0, dot( V, H ) );
    float NV = max( 0.0, dot( N.xyz, V ) );
    float NL = max( 0.0, dot( L, N.xyz ) );

    // Beckmann's distribution function
    float NH2 = NH * NH;
    float m2 = m * m;
    float D = ( 1.0 / m2 * NH2 * NH2 ) * (exp( -( ( 1.0 - NH2 ) / ( m2 * NH2 ) ) ) );

    // Fresnel
    float F = RI + ( 1.0 - RI ) * (max(0.0, min(1.0, pow( ( 1.0 - NV ), 5.0 ))));//max(0.0, min(1.0, x));

    // geometric term
//    float G = min( 1.0, min( ( 2.0 * NH * NL ) / VH, ( 2.0 * NH * NV ) / VH ) );

    float S = ( F * D ) / ( PI * NL * NV );//( F * D * G )

//    ------------------------------
//     Original: Ir = Ai * Ac + Ii(N.L)dw * (Si * Sc + Di * Dc)
//     My formula: Ir = Ia * C + N.L * sat(lidw * (Di * Dc * C + Si * Sc))
//    ------------------------------
    const float Ai = 0.1;
    const float dw = 3.0;
    float Di = DiffuseIntensity;
    float Si = SpecularIntensity * color.w;

    vec4 color_temp =  (dw * ( ( Di * NL * color ) + ( Si * S ) ));
    vec4 color_saturate = vec4(0.0, 0.0, 0.0, 0.0);
    if ( color_temp.x > 0.0 ) color_saturate.x = color_temp.x;
    if ( color_temp.y > 0.0 ) color_saturate.y = color_temp.y;
    if ( color_temp.z > 0.0 ) color_saturate.z = color_temp.z;
    if ( color_temp.w > 0.0 ) color_saturate.w = color_temp.w;
    if ( color_temp.x > 1.0 ) color_saturate.x = 1.0;
    if ( color_temp.y > 1.0 ) color_saturate.y = 1.0;
    if ( color_temp.z > 1.0 ) color_saturate.z = 1.0;
    if ( color_temp.w > 1.0 ) color_saturate.w = 1.0;

    gl_FragColor = Ai * color + ( NL * color_saturate ) * v_Light.w;
}