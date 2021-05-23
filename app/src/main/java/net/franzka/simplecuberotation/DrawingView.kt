package net.franzka.simplecuberotation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/***********************************
 * DrawingView
 * Vue dont on utilisera le canvas pour dessiner le cube
 */
class DrawingView : View {

    companion object {
        private const val TAG = "DrawingView"
        const val STROKE_WIDTH = 10F
        const val PAINT_COLOR = -0x560000
    }

    private val paint = Paint()
    private var cube = Cube()

    private var canvasWidth = 0

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, a: Int) : super(context, attrs, a)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.strokeWidth = STROKE_WIDTH
        paint.color = PAINT_COLOR

        drawCube(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasWidth = w
        cube.createCube((if (w<h) w else h).toFloat() / 2)
    }

    fun rotation() {
        cube.rotation()
    }

    fun setAngles(angleX: Float, angleY: Float, angleZ: Float) {
        cube.apply {
            this.angleX = angleX
            this.angleY = angleY
            this.angleZ = angleZ
        }
    }

    private fun drawCube(canvas: Canvas) {

        val translationX = canvasWidth / 2
        val translationY = cube.arrete

        canvas.drawLine(
            cube.sommets[0].x + translationX,
            cube.sommets[0].y + translationY,
            cube.sommets[1].x + translationX,
            cube.sommets[1].y + translationY,
            paint
        )
        canvas.drawLine(
            cube.sommets[1].x + translationX,
            cube.sommets[1].y + translationY,
            cube.sommets[2].x + translationX,
            cube.sommets[2].y + translationY,
            paint
        )
        canvas.drawLine(
            cube.sommets[2].x + translationX,
            cube.sommets[2].y + translationY,
            cube.sommets[3].x + translationX,
            cube.sommets[3].y + translationY,
            paint
        )
        canvas.drawLine(
            cube.sommets[3].x + translationX,
            cube.sommets[3].y + translationY,
            cube.sommets[0].x + translationX,
            cube.sommets[0].y + translationY,
            paint
        )
        canvas.drawLine(
            cube.sommets[0].x + translationX,
            cube.sommets[0].y + translationY,
            cube.sommets[4].x + translationX,
            cube.sommets[4].y + translationY,
            paint
        )
        canvas.drawLine(
            cube.sommets[1].x + translationX,
            cube.sommets[1].y + translationY,
            cube.sommets[5].x + translationX,
            cube.sommets[5].y + translationY,
            paint
        )
        canvas.drawLine(
            cube.sommets[2].x + translationX,
            cube.sommets[2].y + translationY,
            cube.sommets[6].x + translationX,
            cube.sommets[6].y + translationY,
            paint
        )
        canvas.drawLine(
            cube.sommets[3].x + translationX,
            cube.sommets[3].y + translationY,
            cube.sommets[7].x + translationX,
            cube.sommets[7].y + translationY,
            paint
        )
        canvas.drawLine(
            cube.sommets[4].x + translationX,
            cube.sommets[4].y + translationY,
            cube.sommets[5].x + translationX,
            cube.sommets[5].y + translationY,
            paint
        )
        canvas.drawLine(
            cube.sommets[5].x + translationX,
            cube.sommets[5].y + translationY,
            cube.sommets[6].x + translationX,
            cube.sommets[6].y + translationY,
            paint
        )
        canvas.drawLine(
            cube.sommets[6].x + translationX,
            cube.sommets[6].y + translationY,
            cube.sommets[7].x + translationX,
            cube.sommets[7].y + translationY,
            paint
        )
        canvas.drawLine(
            cube.sommets[7].x + translationX,
            cube.sommets[7].y + translationY,
            cube.sommets[4].x + translationX,
            cube.sommets[4].y + translationY,
            paint
        )
    }
}