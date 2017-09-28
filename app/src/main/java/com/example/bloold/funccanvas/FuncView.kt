package com.example.bloold.funccanvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * Created by bloold on 12.09.17.
 */
class FuncView
    : View, Model {

    companion object {
        private val TAG: String = "FuncView"
    }
    override fun drawView(xB: Double, xE: Double) {
        realxBegin = xB
        realxEnd = xE

        invalidate()
    }

    override fun drawView(xB: Double, xE: Double, yB: Double, yE: Double) {
        realxBegin = xB
        realxEnd = xE

        realyBegin = yB
        realyEnd = yE

        scaleY = ((yEnd - yBegin) / (yE - yB)) * step
        Log.w(TAG, " " + scaleY.toString() + " " + yEnd.toString() + " " +  yBegin.toString() + " " + yE.toString()  + " " + yB.toString())
        invalidate()
    }

    override fun scalePlus() {
        realxBegin += 1
        realxEnd -= 1

        invalidate()
    }

    override fun scaleMinus() {
        realxBegin -= 1
        realxEnd += 1

        invalidate()
    }

    constructor(ctx: Context) : super(ctx) {
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
    }

    lateinit var func: (Double) -> Double

    var xBegin = 50
    val xEnd = 1000

    var yBegin = 50
    val yEnd = 700

    val c = xEnd - xBegin

    var scaleY: Double = 100.0
        get() = field
        set(value) {
            if(value < 0){
                field = 0.0
            }else{
                field = value
            }
        }

    val scaleX = 1
    val scaleStep = 10

    var realxBegin: Double = 0.0
    var realxEnd: Double = 0.0

    var realyBegin: Double = 0.0
    var realyEnd: Double = 0.0

    val halfWindow = (c + 2 * xBegin) / 2

    var step: Double
        get() = (realxEnd - realxBegin) / c.toDouble()
        set(value){

        }

    val paint = Paint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.color = Color.BLACK
        paint.strokeWidth = (10).toFloat()

        canvas?.drawLine(xBegin.toFloat(), halfWindow.toFloat(), xEnd.toFloat(), halfWindow.toFloat(), paint)

        drawFunc(canvas)
    }

    fun drawFunc(canvas: Canvas?){

        var x_r = 0.0;
        if(realxBegin < 0){
            x_r = -realxBegin
        }

        for(x: Int in xBegin.. xEnd){
            val yr = (getY(func(getX(x)))).toFloat()
            Log.w("onDraw", x.toString() + " : " + yr.toString())
            if(yr < getY(realyEnd) && yr > getY(realyBegin))
                Log.w("Draw", x.toString() + " : " + yr.toString())
                canvas?.drawPoint(x.toFloat(), yr, paint)
        }
    }

    fun getX(x: Int): Double{
        return realxBegin + step * (x - xBegin)
    }

    fun getY(y: Double): Int{
        return (y / (-step)).toInt() + halfWindow
    }
}