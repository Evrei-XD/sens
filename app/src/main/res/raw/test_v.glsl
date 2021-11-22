// Define some useful macros
#define saturate(x) clamp( x, 0.0, 1.0 )
#define lerp        mix



uniform mat4 u_MVPMatrix;
uniform mat4 u_MVMatrix;

attribute vec4 a_Position;
attribute vec3 a_Normal;
attribute vec2 a_TexCoordinate;

varying vec3 v_Normal;
varying vec3 g_vViewVecES;
varying vec3 v_TexCoordinate;

void main()
{
    // Compute the eye space position
    vec4 v_Position = u_MVMatrix * a_Position;

    // Compute the clip space position
    gl_Position = u_MVPMatrix * a_Position;

    // Transform object-space normals to eye-space
    v_Normal = normalize(vec3(u_MVMatrix * vec4(a_Normal, 0.0)));

    // Pass the view vector to the fragment shader
    g_vViewVecES = -v_Position.xyz;

    // Pass the texture coordinates to the fragment shader
    v_TexCoordinate = reflect( normalize( v_Position.xyz/v_Position.w ), v_Normal );
}


//--------------------------------------------------------------------------------------
// The main fragment shader
//--------------------------------------------------------------------------------------
