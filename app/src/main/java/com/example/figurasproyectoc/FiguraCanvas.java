package com.example.figurasproyectoc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class FiguraCanvas extends View {
    private String figuraActual = "";

    public FiguraCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    //se llama cada vez que la vista necesita ser dibujada
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(0xFFFFA500); // Color naranja
        paint.setStyle(Paint.Style.FILL);

        if (figuraActual.equals("Triangulo")) {
            float[] puntos = { getWidth() / 2, getHeight() / 4, getWidth() / 4, getHeight() * 3 / 4, getWidth() * 3 / 4, getHeight() * 3 / 4 };
            canvas.drawVertices(Canvas.VertexMode.TRIANGLES, 6, puntos, 0, null, 0, null, 0, null, 0, 0, paint);
        } else if (figuraActual.equals("Cuadrado")) {
            canvas.drawRect(getWidth() / 4, getHeight() / 4, getWidth() * 3 / 4, getHeight() * 3 / 4, paint);
        } else if (figuraActual.equals("Pentagono")) {
            float[] puntos = { getWidth() / 2, getHeight() / 4, getWidth() * 3 / 4, getHeight() / 3, getWidth() * 2 / 3, getHeight() * 3 / 4, getWidth() / 3, getHeight() * 3 / 4, getWidth() / 4, getHeight() / 3 };
            canvas.drawVertices(Canvas.VertexMode.TRIANGLE_FAN, 10, puntos, 0, null, 0, null, 0, null, 0, 0, paint);
        }
    }
    // actualiza la figura actual que se va a dibujar
    public void setFigura(String figura) {
        this.figuraActual = figura;
        invalidate(); // Redibuja el canvas
    }


    //Método que limpia el Canvas y reinicia la figura actual
    public void reiniciarCanvas() {
        this.figuraActual = "";
        invalidate();
    }
}
