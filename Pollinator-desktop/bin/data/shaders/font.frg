#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP 
#endif

uniform sampler2D u_texture;

varying LOWP vec4 v_color;
varying vec2 v_texCoord;

uniform float scale;

uniform float spread;

uniform vec4 shadowColour;

uniform float shadowIntensity;

void main() {
	float smoothing = 0.25 / (spread * scale);
 	float distance = texture2D(u_texture, v_texCoord).a;
    	float alpha = smoothstep(0.5 - smoothing, 0.5 + smoothing, distance);
    	gl_FragColor = vec4(v_color.rgb, alpha * v_color.a);
	gl_FragColor = (gl_FragColor * gl_FragColor.a) + ((vec4(shadowColour.rgb,shadowColour.a * v_color.a) * (shadowIntensity * distance)) * (1.0 - gl_FragColor.a));

}
