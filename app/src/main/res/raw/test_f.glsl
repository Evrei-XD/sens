// default to medium precision
precision mediump float;

// OpenGL ES require that precision is defined for a fragment shader
// usage example: varying NEED_HIGHP vec2 vLargeTexCoord;
#ifdef GL_FRAGMENT_PRECISION_HIGH
#define NEED_HIGHP highp
#else
#define NEED_HIGHP mediump
#endif

// Enable supported extensions
//#extension GL_OES_texture_3D : enable


// Define some useful macros
#define saturate(x) clamp( x, 0.0, 1.0 )
#define lerp        mix



const vec3  g_vAmbient   = vec3(0.2, 0.2, 0.2);
const vec3  g_vDiffuse   = vec3(1.0, 1.0, 0.1);
const vec3  g_vSpecular  = vec3(1.0, 1.0, 1.0);
const float g_fShininess = 64.0;

uniform samplerCube u_RefMap;
uniform samplerCube u_EnvMap;
uniform vec3 u_LightPos;

varying vec3 v_Normal;
varying vec3 g_vViewVecES;
varying vec3 v_TexCoordinate;

void main()
{
    // Normalize per-pixel vectors
    vec3 vNormal = normalize( v_Normal );
    vec3 vLight  = normalize( u_LightPos );
    vec3 vView   = normalize( g_vViewVecES );
    vec3 vHalf   = normalize( vLight + vView );

    // Compute the lighting in eye-space
    float fDiffuse  = max( 0.0, dot( vNormal, vLight ) );
    float fSpecular = pow( max( 0.0, dot( vNormal, vHalf ) ), g_fShininess );

    // Combine lighting with the material properties
    vec4 vColor;
    vColor.rgb = vec3(0.5, 0.2, 0.3);//g_vAmbient + g_vDiffuse*fDiffuse + g_vSpecular*fSpecular;
    vColor.a   = 1.0;

    vec4 vRefColor = textureCube( u_RefMap, v_TexCoordinate );
    vec4 vEnvColor = textureCube( u_EnvMap, v_TexCoordinate );

    gl_FragColor = vColor * ( vEnvColor * ( 1.0 - vRefColor.a ) + ( vRefColor * vRefColor.a ) );// (( 1.0 - vRefColor.a ) + ( vRefColor * vRefColor.a ) );
}

