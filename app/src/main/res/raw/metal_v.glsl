//--------------------------------------------------------------------------------------
// The vertex shader
//--------------------------------------------------------------------------------------

attribute vec4 a_Position;
attribute vec3 a_Normal;
attribute vec2 a_TexCoordinate;
attribute vec3 a_TangentIn;
attribute vec3 a_BitangentIn;

uniform mat4 u_MMatrix;
uniform mat4 u_MVPMatrix;
uniform mat4 u_NormalMatrix;//
uniform vec3 u_LightPos;//
const vec3 u_EyePos = vec3(0.0, 0.0, 150.5);

varying vec2 v_TexCoord;//
varying vec4 v_Light;//
varying vec3 v_View;//


void main()
{
    gl_Position = u_MVPMatrix * a_Position;
    vec4 WorldPos = u_MMatrix * a_Position;
    v_TexCoord = a_TexCoordinate;

    // build a matrix to transform from world space to tangent space
    vec3 Normal = normalize( a_Normal );
    vec3 Tangent = normalize( a_TangentIn );
    mat3 WorldToTangentSpace;
    WorldToTangentSpace[0] = normalize( vec4( Tangent, 1.0 ) ).xyz;//  u_NormalMatrix *
    WorldToTangentSpace[1] = normalize( vec4( cross( Tangent, Normal ), 1.0 ) ).xyz;//  u_NormalMatrix *
    WorldToTangentSpace[2] = normalize( vec4( Normal, 1.0 ) ).xyz;//  u_NormalMatrix *

    // put the light vector into tangent space
    vec3 Light = normalize( u_LightPos - WorldPos.xyz );
    v_Light.xyz = Light * WorldToTangentSpace;

    // put the view vector into tangent space as well
    vec3 Viewer = normalize( u_EyePos - WorldPos.xyz );
    v_View = Viewer * WorldToTangentSpace;

    // simple attenuation
    float LightRange = 10.0;
    v_Light.w = max ( 0.0, min( 1.0, ( 1.0 - dot( Light * LightRange, Light * LightRange ) ) ) );
}
