package com.skydoves.waterdays.ui.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Toast;
import com.skydoves.waterdays.R;

public class GripperSettingsGLSurfaceView extends GLSurfaceView implements ErrorHandler
{
	private final GripperSettingsActivity gripperSettingsActivity;
	private GripperSettingsRenderer renderer;
//	private TextView panelInfo;
	
	// Offsets for touch events	 
    private float previousX;
    private float previousY;
    
    private float density;

    private boolean selectFlag = false;


	
	public GripperSettingsGLSurfaceView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		gripperSettingsActivity = new GripperSettingsActivity();
	}

	@Override
	public void handleError(com.skydoves.waterdays.ui.model.ErrorHandler.ErrorType errorType, String cause) {
		// Queue on UI thread.
		post(() -> {
			final String text;

			if (errorType == ErrorType.BUFFER_CREATION_ERROR) {
				text = String
						.format(getContext().getResources().getString(
								R.string.lesson_eight_error_could_not_create_vbo), cause);
			} else {
				text = String.format(
						getContext().getResources().getString(
								R.string.lesson_eight_error_unknown), cause);
			}

			Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();

		});
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{


		if (event != null)
		{
			float x = event.getX();
			float y = event.getY();

			if (event.getAction() == MotionEvent.ACTION_DOWN)
			{
				if (renderer != null)
				{
					renderer.selectFlag = true;
				}
			}
			if (event.getAction() == MotionEvent.ACTION_MOVE)
			{
				if (renderer != null)
				{
					if (renderer.selectingNowFlag) { renderer.selectFlag = true; }
					float deltaX = (x - previousX) / density / 2f;
					float deltaY = (y - previousY) / density / 2f;

					/** этот блок чтобы пофиксить неработающий зум*/
					//TODO выпилить это после допилки зума и мультитача
					if (deltaX >  30) {deltaX = 0;}
					if (deltaX < -30) {deltaX = 0;}
					if (deltaY >  30) {deltaY = 0;}
					if (deltaY < -30) {deltaY = 0;}

					renderer.deltaX += deltaX;
					renderer.deltaY += deltaY;
				}
			}
			if (event.getAction() == MotionEvent.ACTION_UP)
			{
				if (renderer != null)
				{
					renderer.transferFlag = true;
				}
			}
			assert renderer != null;
			renderer.X = x;
			renderer.Y = y;
			previousX = x;
			previousY = y;
			return true;
		}
		else
		{
			return super.onTouchEvent(null);
		}
	}

	// Hides superclass method.
	public void setRenderer(GripperSettingsRenderer renderer, float density)
	{
		this.renderer = renderer;
		this.density = density;
		super.setRenderer(renderer);
	}

}
