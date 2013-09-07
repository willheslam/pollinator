#version 120

varying vec3 v_normal;

varying vec2 v_texCoord;


void main (void)
{
	
	gl_FragColor = vec4(v_normal,1);

}
