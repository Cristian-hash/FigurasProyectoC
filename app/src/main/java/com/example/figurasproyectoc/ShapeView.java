package com.example.figurasproyectoc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class ShapeView extends View {
    private Paint borderPaint;
    private Paint fillPaint;
    private int sides;

    public ShapeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        borderPaint = new Paint();
        borderPaint.setColor(0xFF000000);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(5);

        fillPaint = new Paint();
        fillPaint.setColor(0xFFFFA500);
        fillPaint.setStyle(Paint.Style.FILL);
        sides = 2;
    }
    public void addSide() {
        sides++;
        invalidate();
    }
    public void reset() {
        sides = 2;
        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (sides < 3) return;
        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;
        float radius = Math.min(centerX, centerY) * 0.8f;
        float angleStep = (float) (2 * Math.PI / sides);
        float startAngle = (float) (-Math.PI / 2);
        float[] points = new float[2 * sides];
        for (int i = 0; i < sides; i++) {
            points[2 * i] = centerX + radius * (float) Math.cos(startAngle + i * angleStep);
            points[2 * i + 1] = centerY + radius * (float) Math.sin(startAngle + i * angleStep);
        }
        canvas.drawPath(createPath(points), fillPaint);
        for (int i = 0; i < sides; i++) {
            int next = (i + 1) % sides;
            canvas.drawLine(points[2 * i], points[2 * i + 1], points[2 * next], points[2 * next + 1], borderPaint);
        }
    }
    private Path createPath(float[] points) {
        Path path = new Path();
        path.moveTo(points[0], points[1]);
        for (int i = 1; i < points.length / 2; i++) {
            path.lineTo(points[2 * i], points[2 * i + 1]);
        }
        path.close();
        return path;
    }
}
