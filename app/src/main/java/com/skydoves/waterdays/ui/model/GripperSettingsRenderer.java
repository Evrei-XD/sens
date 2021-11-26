package com.skydoves.waterdays.ui.model;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Build;
import com.skydoves.waterdays.R;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.skydoves.waterdays.models.ShortWeather;
import com.skydoves.waterdays.presenters.Load3DModelNew;
import com.skydoves.waterdays.common.RawResourceReader;
import com.skydoves.waterdays.common.ShaderHelper;
import com.skydoves.waterdays.common.TextureHelper;
import timber.log.Timber;

import static android.opengl.GLES20.GL_LINEAR;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.GL_TEXTURE_MAG_FILTER;
import static android.opengl.GLES20.glGenerateMipmap;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glTexParameteri;
import static java.lang.Byte.toUnsignedInt;

/**
 * This class implements our custom renderer. Note that the GL10 parameter
 * passed in is unused for OpenGL ES 2.0 renderers -- the static class GLES20 is
 * used instead.
 */
public class GripperSettingsRenderer implements GLSurfaceView.Renderer{
	/** Used for debug logs. */
	private static final String TAG = "LessonEightRenderer";

	/** References to other main objects. */
	private final GripperSettingsActivity gripperSettingsActivity;
	private final ErrorHandler errorHandler;

	/**
	 * Store the model matrix. This matrix is used to move models from object
	 * space (where each model can be thought of being located at the center of
	 * the universe) to world space.
	 */
	private final float[] modelMatrix = new float[16];

	/**
	 * Store the view matrix. This can be thought of as our camera. This matrix
	 * transforms world space to eye space; it positions things relative to our
	 * eye.
	 */
	private final float[] viewMatrix = new float[16];

	/**
	 * Store the projection matrix. This is used to project the scene onto a 2D
	 * viewport.
	 */
	private final float[] projectionMatrix = new float[16];


	/**
	 * Allocate storage for the final combined matrix. This will be passed into
	 * the shader program.
	 */
	private final float[] mvpMatrix = new float[16];

	/** Additional matrices. */
	private final float[] accumulatedRotation = new float[16];
	private final float[] accumulatedRotation2 = new float[16];
	private final float[] accumulatedRotationForeFinger = new float[16];
	private final float[] accumulatedRotationForeFinger2 = new float[16];
	private final float[] accumulatedRotationMiddleFinger = new float[16];
	private final float[] accumulatedRotationMiddleFinger2 = new float[16];
	private final float[] accumulatedRotationRingFinger = new float[16];
	private final float[] accumulatedRotationRingFinger2 = new float[16];
	private final float[] accumulatedRotationLittleFinger = new float[16];
	private final float[] accumulatedRotationLittleFinger2 = new float[16];
	private final float[] accumulatedRotationGeneral = new float[16];
	private final float[] currentRotation = new float[16];
	private final float[] lightModelMatrix = new float[16];
	private final float[] temporaryMatrix = new float[16];

	/** OpenGL handles to our program uniforms. */
	private int mvpMatrixUniform;
	private int mvMatrixUniform;
	private int lightPosUniform;
	private int codeSelectUniform;
	private int textureUniform;
//	private int normalMapUniform;
	private int isUsingNormalMap;
	private int specularFactorUniform;
	private int lightPowerUniform;
	private int ambientFactorUniform;



	/** OpenGL handles to our program attributes. */
	private int positionAttribute;
	private int normalAttribute;
	private int colorAttribute;
	private int texturesAttribute;
	private int tangentAttribute;
	private int bitangentAttribute;



	/** Identifiers for our uniforms and attributes inside the shaders. */
	private static final String MVP_MATRIX_UNIFORM = "u_MVPMatrix";
	private static final String MV_MATRIX_UNIFORM = "u_MVMatrix";
	private static final String LIGHT_POSITION_UNIFORM = "u_LightPos";
	private static final String TEXTURE_UNIFORM = "u_Texture";
//	private static final String NORMAL_MAP_UNIFORM = "u_normalMap";
	private static final String IS_USING_NORMAL_MAP_UNIFORM = "u_isUsingNormalMap";
	private static final String SPECULAR_FACTOR_UNIFORM = "u_specularFactor";
	private static final String LIGHT_POWER_UNIFORM = "u_lightPower";
	private static final String AMBIENT_FACTOR_UNIFORM = "u_ambientFactor";
	private static final String CODE_SELECT_UNIFORM = "u_Code";

	private static final String POSITION_ATTRIBUTE = "a_Position";
	private static final String NORMAL_ATTRIBUTE = "a_Normal";
	private static final String COLOR_ATTRIBUTE = "a_Color";
	private static final String TEXTURES_ATTRIBUTE = "a_TexCoordinate";
	private static final String TANGENT_ATTRIBUTE = "a_TangentIn";
	private static final String BITANGENT_ATTRIBUTE = "a_BitangentIn";



	/** Additional constants. */
	private static final int POSITION_DATA_SIZE_IN_ELEMENTS = 3;
	private static final int NORMAL_DATA_SIZE_IN_ELEMENTS = 3;
	private static final int COLOR_DATA_SIZE_IN_ELEMENTS = 4;
	private static final int TEXTURES_DATA_SIZE_IN_ELEMENTS = 2;
	private static final int TANGENT_DATA_SIZE_IN_ELEMENTS = 3;
	private static final int BITANGENT_DATA_SIZE_IN_ELEMENTS = 3;

	private static final int BYTES_PER_FLOAT = 4;
	private static final int BYTES_PER_INT = 4;

	private static final int STRIDE = (POSITION_DATA_SIZE_IN_ELEMENTS + NORMAL_DATA_SIZE_IN_ELEMENTS
			+ COLOR_DATA_SIZE_IN_ELEMENTS + TEXTURES_DATA_SIZE_IN_ELEMENTS + TANGENT_DATA_SIZE_IN_ELEMENTS
			+ BITANGENT_DATA_SIZE_IN_ELEMENTS ) * BYTES_PER_FLOAT;//+ BITANGENT_DATA_SIZE_IN_ELEMENTS)



	/**
	 * Used to hold a light centered on the origin in model space. We need a 4th
	 * coordinate so we can get translations to work when we multiply this by
	 * our transformation matrices.
	 */
	private final float[] lightPosInModelSpace = new float[] { 0.0f, 0.0f, 0.0f, 1.0f };

	/**
	 * Used to hold the current position of the light in world space (after
	 * transformation via model matrix).
	 */
	private final float[] lightPosInWorldSpace = new float[4];

	/**
	 * Used to hold the transformed position of the light in eye space (after
	 * transformation via modelview matrix)
	 */
	private final float[] lightPosInEyeSpace = new float[4];

	/** This is a handle to our cube shading program. */
	private int program;
//	private int programWithColor;
	private int programSelect;


	/** Retain the most recent delta for touch events. */
	// These still work without volatile, but refreshes are not guaranteed to
	// happen.
	public volatile float X;
	public volatile float Y;
	public volatile float deltaX;
	public volatile float deltaY;
	public int width;
	public int height;
	public boolean selectFlag;//флаг, позволяющий выбрать фрагмент при опускании пальца на экран
	public boolean selectingNowFlag;//флаг, информирующий о том, выбран сейчас хоть один фрагмент или нет
	public boolean transferFlag;
	private boolean firstInit = false;
	private final int[] selectionStationFragments = new int[MAX_NUMBER_DETAILS+2]; //массив 0-1 состояния каждого объекта
	private ArrayList<Integer> selectFragment = new ArrayList<>(); //номера фрагментов, списка отрисовываются выделенными
	private ArrayList<Integer> unselectFragment = new ArrayList<>(); //номера фрагментов, списка отрисовываются невыделенными
//	private boolean firstPrint = true;

	/** The current heightmap object. */
	private HeightMap heightMap;

	/** массивы вершин и индексов в которые упаковываются данные из строковых переменных*/
	private float angle90 = 90;
	private static final int MAX_NUMBER_DETAILS = 134;//134
	private int lastSelectTemp = 0;
	private int selectTemp = 0;

	public String selectStation;
	/**
	 * Initialize the model data.
	 */
	public GripperSettingsRenderer(final GripperSettingsActivity gripperSettingsActivity, ErrorHandler errorHandler) {
		this.gripperSettingsActivity = gripperSettingsActivity;
		this.errorHandler = errorHandler;
	}

	@SuppressLint("InlinedApi")
	@Override
	public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
		heightMap = new HeightMap();
		heightMap.loader();

		GLES20.glEnable(GLES20.GL_DEPTH_TEST);
		GLES20.glEnable(GLES20.GL_COLOR_BUFFER_BIT);

		// Position the eye in front of the origin.
		final float eyeX = 0.0f;
		final float eyeY = 0.0f;
		final float eyeZ = 180.0f;

		// We are looking toward the distance (бесполезная хрень, не на что невлияет)
		final float lookX = 0.0f;
		final float lookY = -25.0f;
		final float lookZ = 0.0f;

		// Set our up vector. This is where our head would be pointing were we
		// holding the camera.
		final float upX = 0.0f;
		final float upY = 1.0f;
		final float upZ = 0.0f;

		// Set the view matrix. This matrix can be said to represent the camera
		// position.
		// NOTE: In OpenGL 1, a ModelView matrix is used, which is a combination
		// of a model and view matrix. In OpenGL 2, we can keep track of these
		// matrices separately if we choose.
		Matrix.setLookAtM(viewMatrix, 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);

		final String vertexShader = RawResourceReader.readTextFileFromRawResource(gripperSettingsActivity, R.raw.per_pixel_vertex_shader_tex_and_light);
		final String fragmentShader = RawResourceReader.readTextFileFromRawResource(gripperSettingsActivity, R.raw.per_pixel_fragment_shader_general);
//		final String fragmentShaderWithColor = RawResourceReader.readTextFileFromRawResource(gripperSettingsActivity, R.raw.per_pixel_fragment_shader_tex_color_light);
//		final String fragmentShaderRubber = RawResourceReader.readTextFileFromRawResource(gripperSettingsActivity, R.raw.per_pixel_fragment_shader_rubber);
//		final String fragmentShaderRubberWithColor = RawResourceReader.readTextFileFromRawResource(gripperSettingsActivity, R.raw.per_pixel_fragment_shader_rubber_with_color);
		final String selectVertexShader = RawResourceReader.readTextFileFromRawResource(gripperSettingsActivity, R.raw.select_vertex_shader);
		final String selectFragmentShader = RawResourceReader.readTextFileFromRawResource(gripperSettingsActivity, R.raw.select_fragment_shader);
//		final String testVertexShader = RawResourceReader.readTextFileFromRawResource(gripperSettingsActivity, R.raw.test_v);
//		final String testFragmentShader = RawResourceReader.readTextFileFromRawResource(gripperSettingsActivity, R.raw.test_f);
//		final String vertexShaderMetal = RawResourceReader.readTextFileFromRawResource(gripperSettingsActivity, R.raw.metal_v);
//		final String fragmentShaderMetal = RawResourceReader.readTextFileFromRawResource(gripperSettingsActivity, R.raw.metal_f);
//		final String vertexShaderMetall = RawResourceReader.readTextFileFromRawResource(gripperSettingsActivity, R.raw.metall_v);
//		final String fragmentShaderMetall = RawResourceReader.readTextFileFromRawResource(gripperSettingsActivity, R.raw.metall_f);


		final int vertexShaderHandle = ShaderHelper.compileShader(GLES20.GL_VERTEX_SHADER, vertexShader);
		final int fragmentShaderHandle = ShaderHelper.compileShader(GLES20.GL_FRAGMENT_SHADER, fragmentShader);
//		final int fragmentShaderWithColorHandle = ShaderHelper.compileShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderWithColor);
//		final int fragmentShaderRubberHandle = ShaderHelper.compileShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderRubber);
//		final int fragmentShaderRubberWithColorHandle = ShaderHelper.compileShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderRubberWithColor);
		final int selectVertexShaderHandle = ShaderHelper.compileShader(GLES20.GL_VERTEX_SHADER, selectVertexShader);
		final int selectFragmentShaderHandle = ShaderHelper.compileShader(GLES20.GL_FRAGMENT_SHADER, selectFragmentShader);
//		final int vertexShaderMetallHandle = ShaderHelper.compileShader(GLES20.GL_VERTEX_SHADER, vertexShaderMetall);
//		final int fragmentShaderMetallHandle = ShaderHelper.compileShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderMetall);


		program = ShaderHelper.createAndLinkProgram(vertexShaderHandle, fragmentShaderHandle, new String[] {
				POSITION_ATTRIBUTE, NORMAL_ATTRIBUTE, COLOR_ATTRIBUTE, TEXTURES_ATTRIBUTE, TANGENT_ATTRIBUTE, BITANGENT_ATTRIBUTE});
//		programWithColor = ShaderHelper.createAndLinkProgram(vertexShaderHandle, fragmentShaderWithColorHandle, new String[]{
//				POSITION_ATTRIBUTE, NORMAL_ATTRIBUTE, COLOR_ATTRIBUTE, TEXTURES_ATTRIBUTE, TANGENT_ATTRIBUTE, BITANGENT_ATTRIBUTE});
//		int programRubber = ShaderHelper.createAndLinkProgram(vertexShaderHandle, fragmentShaderRubberHandle, new String[]{
//				POSITION_ATTRIBUTE, NORMAL_ATTRIBUTE, COLOR_ATTRIBUTE, TEXTURES_ATTRIBUTE});
//		int programRubberWithColor = ShaderHelper.createAndLinkProgram(vertexShaderHandle, fragmentShaderRubberWithColorHandle, new String[]{
//				POSITION_ATTRIBUTE, NORMAL_ATTRIBUTE, COLOR_ATTRIBUTE, TEXTURES_ATTRIBUTE});
		programSelect = ShaderHelper.createAndLinkProgram(selectVertexShaderHandle, selectFragmentShaderHandle,
				new String[] {POSITION_ATTRIBUTE});
//		programTestMetal = ShaderHelper.createAndLinkProgram(testVertexShaderHandle, testFragmentShaderHandle,
//				new String[] {POSITION_ATTRIBUTE, NORMAL_ATTRIBUTE, TEXTURES_ATTRIBUTE});
//		programMetal = ShaderHelper.createAndLinkProgram(vertexShaderMetalHandle, fragmentShaderMetalHandle,
//				new String[] {POSITION_ATTRIBUTE, NORMAL_ATTRIBUTE, TEXTURES_ATTRIBUTE, TANGENT_ATTRIBUTE});
//		int programMetall = ShaderHelper.createAndLinkProgram(vertexShaderMetallHandle, fragmentShaderMetallHandle,
//				new String[]{POSITION_ATTRIBUTE, NORMAL_ATTRIBUTE, COLOR_ATTRIBUTE, TEXTURES_ATTRIBUTE,
//						TANGENT_ATTRIBUTE, BITANGENT_ATTRIBUTE});

		//Load the texture
		/** Thise are handle to our texture data.*/
		int gray = TextureHelper.loadTexture(gripperSettingsActivity, R.drawable.gray);
		glGenerateMipmap(GL_TEXTURE_2D);
		GLES20.glBindTexture(GL_TEXTURE_2D, gray);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		//Load the texture5
		GLES20.glActiveTexture(GLES20.GL_TEXTURE4);
		int green = TextureHelper.loadTexture(gripperSettingsActivity, R.drawable.green);
		glGenerateMipmap(GL_TEXTURE_2D);
		GLES20.glBindTexture(GL_TEXTURE_2D, green);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		//Load the texture6
		GLES20.glActiveTexture(GLES20.GL_TEXTURE5);
		int textureSTR2Part10 = TextureHelper.loadTexture(gripperSettingsActivity, R.drawable.select_part);
		glGenerateMipmap(GL_TEXTURE_2D);
		GLES20.glBindTexture(GL_TEXTURE_2D, textureSTR2Part10);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);




		// Initialize the accumulated rotation matrix
		Matrix.setIdentityM(accumulatedRotation, 0);
		Matrix.setIdentityM(accumulatedRotation2, 0);
		Matrix.setIdentityM(accumulatedRotationForeFinger, 0);
		Matrix.setIdentityM(accumulatedRotationForeFinger2, 0);
		Matrix.setIdentityM(accumulatedRotationMiddleFinger, 0);
		Matrix.setIdentityM(accumulatedRotationMiddleFinger2, 0);
		Matrix.setIdentityM(accumulatedRotationRingFinger, 0);
		Matrix.setIdentityM(accumulatedRotationRingFinger2, 0);
		Matrix.setIdentityM(accumulatedRotationLittleFinger, 0);
		Matrix.setIdentityM(accumulatedRotationLittleFinger2, 0);
		Matrix.setIdentityM(accumulatedRotationGeneral, 0);
		selectStation = "UNSELECTED_OBJECT";

		//обнуленуление всего массива выбранных объектов
		for (int i = 0; i<=MAX_NUMBER_DETAILS; i++) {
			selectionStationFragments[i] = 0;
			unselectFragment.add(i);
		}
	}

	@Override
	public void onSurfaceChanged(GL10 glUnused, int width, int height) {
		// Set the OpenGL viewport to the same size as the surface.
		GLES20.glViewport(0, 0, width, height);
		this.width = width;
		this.height = height;

		// Create a new perspective projection matrix. The height will stay the
		// same while the width will vary as per aspect ratio.
		final float ratio = (float) width / height;
		final float left = -ratio;
		final float bottom = -1.0f;
		final float top = 1.0f;
		final float near = 1.0f;
		final float far = 300.0f;//2000

		Matrix.frustumM(projectionMatrix, 0, left, ratio, bottom, top, near, far);
	}

	@Override
	public void onDrawFrame(GL10 glUnused) {
		if (selectFlag){
			selectTemp = selectObject();
			for (int i = 0; i<MAX_NUMBER_DETAILS; i++) {
				if (selectTemp == i){
					selectStation = "SELECT_PART_" + i;
				}
			}
		}
		if(transferFlag){
			transferCommand();
		}

		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

		if(firstInit) { firstInit (); firstInit=false; }

		/** вращающийся источник света */
		Matrix.setIdentityM(lightModelMatrix, 0);
		Matrix.translateM(lightModelMatrix, 0, 0.0f, 0.0f, 180.0f);
		Matrix.multiplyMV(lightPosInWorldSpace, 0, lightModelMatrix, 0, lightPosInModelSpace, 0);
		Matrix.multiplyMV(lightPosInEyeSpace, 0, viewMatrix, 0, lightPosInWorldSpace, 0);

		/** код загрузки всех деталей руки в начальные координаты для возвращения большого пальца в начальное положение в конструкции*/
		Matrix.setIdentityM(modelMatrix, 0);
		Matrix.translateM(modelMatrix, 0, 0.0f, 0.0f, 0.0f);

		/** поворот всей сборки */
		Matrix.setIdentityM(currentRotation, 0);
		Matrix.rotateM(currentRotation, 0, angle90, 0.0f, 0.0f, 1.0f);
		Matrix.rotateM(currentRotation, 0, angle90, 1.0f, 0.0f, 0.0f);
		angle90 = 0;

		if (selectTemp == 0) {
			Matrix.rotateM(currentRotation, 0, deltaY, 1.0f, 0.0f, 0.0f);
			Matrix.rotateM(currentRotation, 0, deltaX, 0.0f, 1.0f, 0.0f);
			deltaX = 0.0f;
			deltaY = 0.0f;
		}

		Matrix.multiplyMM(temporaryMatrix, 0, currentRotation, 0, accumulatedRotationGeneral, 0);
		System.arraycopy(temporaryMatrix, 0, accumulatedRotationGeneral, 0, 16);

		Matrix.multiplyMM(temporaryMatrix, 0, accumulatedRotationGeneral, 0, modelMatrix, 0);
		System.arraycopy(temporaryMatrix, 0, modelMatrix, 0, 16);

		/** составления матриц вида и проекции */
		Matrix.multiplyMM(mvpMatrix, 0, viewMatrix, 0, modelMatrix, 0);
		GLES20.glUniformMatrix4fv(mvMatrixUniform, 1, false, mvpMatrix, 0);
		Matrix.multiplyMM(temporaryMatrix, 0, projectionMatrix, 0, mvpMatrix, 0);
		System.arraycopy(temporaryMatrix, 0, mvpMatrix, 0, 16);
		GLES20.glUniformMatrix4fv(mvpMatrixUniform, 1, false, mvpMatrix, 0);
		GLES20.glUniform3f(lightPosUniform, lightPosInEyeSpace[0], lightPosInEyeSpace[1], lightPosInEyeSpace[2]);

		GLES20.glUseProgram(program);

		mvpMatrixUniform = glGetUniformLocation(program, MVP_MATRIX_UNIFORM);
		mvMatrixUniform = glGetUniformLocation(program, MV_MATRIX_UNIFORM);
		positionAttribute = GLES20.glGetAttribLocation(program, POSITION_ATTRIBUTE);
		normalAttribute = GLES20.glGetAttribLocation(program, NORMAL_ATTRIBUTE);
		colorAttribute = GLES20.glGetAttribLocation(program, COLOR_ATTRIBUTE);
		texturesAttribute = GLES20.glGetAttribLocation(program, TEXTURES_ATTRIBUTE);
		tangentAttribute = GLES20.glGetAttribLocation(program, TANGENT_ATTRIBUTE);
		bitangentAttribute = GLES20.glGetAttribLocation(program, BITANGENT_ATTRIBUTE);
		lightPosUniform = glGetUniformLocation(program, LIGHT_POSITION_UNIFORM);
		textureUniform = glGetUniformLocation(program, TEXTURE_UNIFORM);
//		normalMapUniform = glGetUniformLocation(program, NORMAL_MAP_UNIFORM);
		isUsingNormalMap = glGetUniformLocation(program, IS_USING_NORMAL_MAP_UNIFORM);
		specularFactorUniform = glGetUniformLocation(program, SPECULAR_FACTOR_UNIFORM);
		lightPowerUniform = glGetUniformLocation(program, LIGHT_POWER_UNIFORM);
		ambientFactorUniform = glGetUniformLocation(program, AMBIENT_FACTOR_UNIFORM);

		Matrix.multiplyMM(mvpMatrix, 0, viewMatrix, 0, modelMatrix, 0);
		GLES20.glUniformMatrix4fv(mvMatrixUniform, 1, false, mvpMatrix, 0);
		Matrix.multiplyMM(temporaryMatrix, 0, projectionMatrix, 0, mvpMatrix, 0);
		System.arraycopy(temporaryMatrix, 0, mvpMatrix, 0, 16);
		GLES20.glUniformMatrix4fv(mvpMatrixUniform, 1, false, mvpMatrix, 0);
		GLES20.glUniform3f(lightPosUniform, lightPosInEyeSpace[0], lightPosInEyeSpace[1], lightPosInEyeSpace[2]);

		GLES20.glUniform1i(isUsingNormalMap, 0);
		GLES20.glUniform1f(specularFactorUniform, 2.0f);
		GLES20.glUniform1f(lightPowerUniform, 700.0f);
		GLES20.glUniform1f(ambientFactorUniform, 0.92f);
		GLES20.glUniform1i(textureUniform, 4);

		for (int i = 1; i<=MAX_NUMBER_DETAILS; i+=2){
			if (selectionStationFragments[i+1] == 0) {
				heightMap.render(new int[]{i});
			}
		}

		GLES20.glUniform1i(isUsingNormalMap, 0);
		GLES20.glUniform1f(specularFactorUniform, 2.0f);
		GLES20.glUniform1f(lightPowerUniform, 700.0f);
		GLES20.glUniform1f(ambientFactorUniform, 0.92f);
		GLES20.glUniform1i(textureUniform, 3);


		for (int i = 0; i<MAX_NUMBER_DETAILS; i+=2){
			if ( i != 16 ) {//куски 16 и 2 встают на одно место поэтому 16-й выпиливается
				if (selectionStationFragments[i+1] == 0) {
					heightMap.render(new int[]{i});
				}
			}
		}





		if (selectTemp != lastSelectTemp ) {
			System.err.println("============================================");
			System.err.println("++++++++++++++++++++++++++++++++++++++++++++");
//			System.err.println("selectTemp != lastSelectTemp");
			if (selectTemp == 0 ) {
				//TODO
//				System.err.println("selectTemp = 0");
				selectingNowFlag = false;
				unselectFragment.clear();
				for (int i = 0; i<=MAX_NUMBER_DETAILS; i++) {
					selectionStationFragments[i] = 0;
					unselectFragment.add(i);
				}
				selectFragment.clear();
			} else {
//				System.err.println("selectTemp != 0");
				selectingNowFlag = true;
				if (selectionStationFragments[selectTemp] == 0) {
					selectionStationFragments[selectTemp] = 1;
				} else {
					selectionStationFragments[selectTemp] = 0;
				}

				boolean check = false;
				for (int i = 0; i<selectFragment.size(); i++){
					if (selectFragment.get(i) == selectTemp) {
						check = true;
						selectFragment.remove(i);
					}
				}
				if (!check) { selectFragment.add(selectTemp); }

				boolean check2 = false;
				for (int i = 0; i<unselectFragment.size(); i++){
					if (unselectFragment.get(i) == selectTemp) {
						check2 = true;
						unselectFragment.remove(i);
					}
				}
				if (!check2) { unselectFragment.add(selectTemp); }
			}
			lastSelectTemp = selectTemp;
			for (int j = 0; j<selectFragment.size(); j++){
				System.err.println(selectFragment.get(j));
				GripperSettingsActivity.Companion.setSelectPartsNum(selectFragment);
			}
//			System.err.println("============================================");
			for (int j = 0; j<unselectFragment.size(); j++){
//				System.err.println(unselectFragment.get(j));
			}
			System.err.println("++++++++++++++++++++++++++++++++++++++++++++");
			System.err.println("============================================\n");
		} else {
			if ( !GripperSettingsActivity.Companion.getSelectionState() ) {
				selectingNowFlag = false;
				unselectFragment.clear();
				for (int i = 0; i<MAX_NUMBER_DETAILS; i++) {
					selectionStationFragments[i] = 0;
					unselectFragment.add(i);
				}
				selectFragment.clear();
				GripperSettingsActivity.Companion.setSelectionState(true);
			}
 		}




//		System.err.println("============================================");
//		System.err.println("============================================");
		GLES20.glUniform1i(isUsingNormalMap, 0);
		GLES20.glUniform1f(specularFactorUniform, 2.0f);
		GLES20.glUniform1f(lightPowerUniform, 700.0f);
		GLES20.glUniform1f(ambientFactorUniform, 0.92f);
		GLES20.glUniform1i(textureUniform, 5);

		for (int j = 0; j<selectFragment.size(); j++){
			for (int i = 0; i<MAX_NUMBER_DETAILS; i++){
				if (selectFragment.get(j) == i+1) {
					heightMap.render(new int[]{i});
//					System.err.println("Выбрана деталь №"+i);
				}
			}
		}
//		System.err.println("============================================");
//		System.err.println("============================================");
	}

	private void firstInit () {  }

	private int selectObject () {
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		GLES20.glUseProgram(programSelect);

		mvpMatrixUniform = GLES20.glGetUniformLocation(programSelect, MVP_MATRIX_UNIFORM);
		mvMatrixUniform = GLES20.glGetUniformLocation(programSelect, MV_MATRIX_UNIFORM);
		codeSelectUniform = GLES20.glGetUniformLocation(programSelect, CODE_SELECT_UNIFORM);
		positionAttribute = GLES20.glGetAttribLocation(programSelect, POSITION_ATTRIBUTE);

		Matrix.setIdentityM(modelMatrix, 0);
		Matrix.translateM(modelMatrix, 0, 0.0f, 0.0f, 0.0f);

		if (selectTemp == 0) {
			/** поворот всей сборки */
			Matrix.setIdentityM(currentRotation, 0);
			Matrix.rotateM(currentRotation, 0, deltaY, 0.0f, 1.0f, 0.0f);
			Matrix.rotateM(currentRotation, 0, deltaX, 1.0f, 0.0f, 0.0f);
			deltaX = 0.0f;
			deltaY = 0.0f;
		}

		Matrix.multiplyMM(temporaryMatrix, 0, currentRotation, 0, accumulatedRotationGeneral, 0);
		System.arraycopy(temporaryMatrix, 0, accumulatedRotationGeneral, 0, 16);

		Matrix.multiplyMM(temporaryMatrix, 0, accumulatedRotationGeneral, 0, modelMatrix, 0);
		System.arraycopy(temporaryMatrix, 0, modelMatrix, 0, 16);

		/** составления матриц вида и проекции */
		Matrix.multiplyMM(mvpMatrix, 0, viewMatrix, 0, modelMatrix, 0);
		GLES20.glUniformMatrix4fv(mvMatrixUniform, 1, false, mvpMatrix, 0);
		Matrix.multiplyMM(temporaryMatrix, 0, projectionMatrix, 0, mvpMatrix, 0);
		System.arraycopy(temporaryMatrix, 0, mvpMatrix, 0, 16);
		GLES20.glUniformMatrix4fv(mvpMatrixUniform, 1, false, mvpMatrix, 0);

		for (int i = 0; i<MAX_NUMBER_DETAILS; i++) {
			GLES20.glUniform1f(codeSelectUniform, i+1);
			heightMap.render(new int[]{i});
		}

		int[] viewport = new int[4];
		GLES20.glGetIntegerv(GLES20.GL_VIEWPORT, IntBuffer.wrap(viewport));
		ByteBuffer res = ByteBuffer.allocateDirect(4);
		GLES20.glReadPixels((int) X, (int) (viewport[3]-Y), 1, 1, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, res);//GL_UNSIGNED_BYTE

		/** сброс флага выделения и дельт*/
		selectFlag = false;
		deltaX = 0.0f;
		deltaY = 0.0f;

//		System.err.println("selectObject Выбрана деталь №"+ castUnsignedCharToInt(res.get(0)));
		return castUnsignedCharToInt(res.get(0));
	}

	@TargetApi(Build.VERSION_CODES.O)
	private int castUnsignedCharToInt(Byte Ubyte) {
		int cast = toUnsignedInt(Ubyte);
		if (cast < 0) {
			cast += 256;
		}
		return cast;
	}

	private void transferCommand() {
//		if(String.valueOf(selectStation).equals("UNSELECTED_OBJECT")){}
//		if(String.valueOf(selectStation).equals("SELECT_FINGER_1")){System.err.println("LessonEightActivity--------> angleLittleFingerTransfer: "+ angleLittleFingerTransfer);}
//		if(String.valueOf(selectStation).equals("SELECT_FINGER_2")){System.err.println("LessonEightActivity--------> angleRingFingerTransfer: "+ angleRingFingerTransfer);}
//		if(String.valueOf(selectStation).equals("SELECT_FINGER_3")){System.err.println("LessonEightActivity--------> angleMiddleFingerTransfer: "+ angleMiddleFingerTransfer);}
//		if(String.valueOf(selectStation).equals("SELECT_FINGER_4")){System.err.println("LessonEightActivity--------> angleForeFingerTransfer: "+ angleForeFingerTransfer);}
//		if(String.valueOf(selectStation).equals("SELECT_FINGER_5")){System.err.println("LessonEightActivity--------> angleBigFingerTransfer1: "+ (100-((int)((float)(angleBigFingerTransfer1+60)/90*100))));
//																	далее конструкция инвертирования и приведения диапазона для вращения венца большого пальца
//																	System.err.println("LessonEightActivity--------> angleBigFingerTransfer2: "+ (100-((int)((float)angleBigFingerTransfer2/90*100))));}
//		transferFlag = false;
	}

	class HeightMap {
		final int[] vbo = new int[MAX_NUMBER_DETAILS];
		final int[] ibo = new int[MAX_NUMBER_DETAILS];

		int indexCount;

		private int i = 0;

		@SuppressLint("LogNotTimber")
		void loader() {
			try {
				GLES20.glGenBuffers(MAX_NUMBER_DETAILS, vbo, 0);
				GLES20.glGenBuffers(MAX_NUMBER_DETAILS, ibo, 0);

				for (i = 0; i<MAX_NUMBER_DETAILS; i++){
					indexCount = Load3DModelNew.getVertexArray(i).length;
					System.err.println("HeightMap--------> количество элементов в массиве №"+(i+1)+" "+indexCount);

					final FloatBuffer heightMapVertexDataBuffer = ByteBuffer
							.allocateDirect(Load3DModelNew.getVertexArray(i).length * BYTES_PER_FLOAT).order(ByteOrder.nativeOrder())
							.asFloatBuffer();
					heightMapVertexDataBuffer.put(Load3DModelNew.getVertexArray(i)).position(0);

					final IntBuffer heightMapIndexDataBuffer = ByteBuffer
							.allocateDirect(Load3DModelNew.getVertexArray(i).length * BYTES_PER_INT).order(ByteOrder.nativeOrder())
							.asIntBuffer();
					heightMapIndexDataBuffer.put(Load3DModelNew.getIndicesArray(i)).position(0);

					if (vbo[0] > 0 && ibo[0] > 0) {
						GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[i]);
						GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, heightMapVertexDataBuffer.capacity() * BYTES_PER_FLOAT,
								heightMapVertexDataBuffer, GLES20.GL_STATIC_DRAW);


						GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, ibo[i]);
						GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, heightMapIndexDataBuffer.capacity()
								* BYTES_PER_INT, heightMapIndexDataBuffer, GLES20.GL_STATIC_DRAW);

						GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, i);
						GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, i);
					} else
						errorHandler.handleError(ErrorHandler.ErrorType.BUFFER_CREATION_ERROR, "glGenBuffers");
				}
			} catch (Throwable t) {
				Timber.tag(TAG).w(t);
				errorHandler.handleError(ErrorHandler.ErrorType.BUFFER_CREATION_ERROR, t.getLocalizedMessage());
			}
		}

		void render(int[] indexesOfBuffer) {
			for (i = 0; i<indexesOfBuffer.length; i++) {
				if (vbo[0] > 0 && ibo[0] > 0) {
					GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[indexesOfBuffer[i]]);

					// Bind Attributes
					GLES20.glVertexAttribPointer(positionAttribute, POSITION_DATA_SIZE_IN_ELEMENTS, GLES20.GL_FLOAT, false,
							STRIDE, 0);
					GLES20.glEnableVertexAttribArray(positionAttribute);

					GLES20.glVertexAttribPointer(normalAttribute, NORMAL_DATA_SIZE_IN_ELEMENTS, GLES20.GL_FLOAT, false,
							STRIDE, POSITION_DATA_SIZE_IN_ELEMENTS * BYTES_PER_FLOAT);
					GLES20.glEnableVertexAttribArray(normalAttribute);

					GLES20.glVertexAttribPointer(colorAttribute, COLOR_DATA_SIZE_IN_ELEMENTS, GLES20.GL_FLOAT, false,
							STRIDE, (POSITION_DATA_SIZE_IN_ELEMENTS + NORMAL_DATA_SIZE_IN_ELEMENTS) * BYTES_PER_FLOAT);
					GLES20.glEnableVertexAttribArray(colorAttribute);

					GLES20.glVertexAttribPointer(texturesAttribute, TEXTURES_DATA_SIZE_IN_ELEMENTS, GLES20.GL_FLOAT, false,
							STRIDE,
							(POSITION_DATA_SIZE_IN_ELEMENTS + NORMAL_DATA_SIZE_IN_ELEMENTS + COLOR_DATA_SIZE_IN_ELEMENTS) * BYTES_PER_FLOAT);
					GLES20.glEnableVertexAttribArray(texturesAttribute);

					GLES20.glVertexAttribPointer(tangentAttribute, TANGENT_DATA_SIZE_IN_ELEMENTS, GLES20.GL_FLOAT, false,
							STRIDE,
							(POSITION_DATA_SIZE_IN_ELEMENTS + NORMAL_DATA_SIZE_IN_ELEMENTS + COLOR_DATA_SIZE_IN_ELEMENTS + TEXTURES_DATA_SIZE_IN_ELEMENTS) * BYTES_PER_FLOAT);
					GLES20.glEnableVertexAttribArray(tangentAttribute);

					GLES20.glVertexAttribPointer(bitangentAttribute, BITANGENT_DATA_SIZE_IN_ELEMENTS, GLES20.GL_FLOAT, false,
							STRIDE,
							(POSITION_DATA_SIZE_IN_ELEMENTS + NORMAL_DATA_SIZE_IN_ELEMENTS + COLOR_DATA_SIZE_IN_ELEMENTS
									+ TEXTURES_DATA_SIZE_IN_ELEMENTS + TANGENT_DATA_SIZE_IN_ELEMENTS) * BYTES_PER_FLOAT);
					GLES20.glEnableVertexAttribArray(bitangentAttribute);

					// Draw
					GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, ibo[indexesOfBuffer[i]]);
					GLES20.glDrawElements(GLES20.GL_TRIANGLES, indexCount, GLES20.GL_UNSIGNED_INT, 0);

					GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, indexesOfBuffer[i]);
					GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, indexesOfBuffer[i]);
				}
			}
		}
	}
}
