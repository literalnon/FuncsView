package com.example.bloold.funccanvas

/**
 * Created by bloold on 12.09.17.
 */

interface BasePresenter {
    fun drawFun(xB: Int, xE: Int)
    fun drawFun(xB: Int, xE: Int, yB: Int, yE: Int)

    fun scalePlus()
    fun scaleMinus()
}

interface Model {
    fun drawView(xB: Double, xE: Double)
    fun drawView(xB: Double, xE: Double, yB: Double, yE: Double)

    fun scalePlus()
    fun scaleMinus()
}

class Presenter(model: Model) : BasePresenter {

    override fun drawFun(xB: Int, xE: Int) {
        model.drawView(xB.toDouble(), xE.toDouble())
    }

    val model: Model = model

    override fun drawFun(xB: Int, xE: Int, yB: Int, yE: Int) {
        model.drawView(xB.toDouble(), xE.toDouble(), yB.toDouble(), yE.toDouble())
    }

    override fun scalePlus() {
        model.scalePlus()
    }

    override fun scaleMinus() {
        model.scaleMinus()
    }

}