package com.example.bloold.funccanvas

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {


    lateinit var btnPlus: Button
    lateinit var btnLess: Button
    lateinit var btnDraw: Button
    lateinit var btnFuncs: Button

    lateinit var etStartX: EditText
    lateinit var etEndX:EditText

    lateinit var etStartY: EditText
    lateinit var etEndY:EditText

    lateinit var lvFuncs:ListView

    lateinit var presenter: BasePresenter
    public lateinit var funcView: FuncView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        funcView = findViewById(R.id.fvMain) as FuncView
        funcView.func = {x -> x}
        presenter = Presenter(funcView)

        btnPlus = findViewById(R.id.btnMore) as Button
        btnLess = findViewById(R.id.btnLess) as Button
        btnDraw = findViewById(R.id.startDraw) as Button
        btnFuncs = findViewById(R.id.btnFuncs) as Button

        etStartX = findViewById(R.id.etxBegin) as EditText
        etEndX = findViewById(R.id.etxEnd) as EditText

        etStartY = findViewById(R.id.etyBegin) as EditText
        etEndY = findViewById(R.id.etyEnd) as EditText

        lvFuncs = findViewById(R.id.lvFunc) as ListView

        var listFunc = HashMap<String, (Double) -> Double>()

        listFunc.put("sin(x)", {x -> Math.sin(x)})
        listFunc.put("cos(x)", {x -> Math.cos(x)})
        listFunc.put("tan(x)", {x -> Math.tan(x)})
        listFunc.put("(x)", {x -> x})
        listFunc.put("(x) ^ 2", {x -> x * x})
        listFunc.put("(x) ^ 3", {x -> x * x * x})

        lvFuncs.adapter = ListAdapter(listFunc, this)
        setListener()
    }

    fun setListener(){
        btnDraw.setOnClickListener {
            val sxB: String = etStartX.text.toString()
            val sxE: String = etEndX.text.toString()

            val syB: String = etStartY.text.toString()
            val syE: String = etEndY.text.toString()

            try {
                val ixB = sxB.toInt()
                val ixE = sxE.toInt()

                val iyB = syB.toInt()
                val iyE = syE.toInt()

                presenter.drawFun(ixB, ixE, iyB, iyE)

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "enter right coordinates", Toast.LENGTH_LONG).show()
            }
        }

        btnLess.setOnClickListener { presenter.scaleMinus() }
        btnPlus.setOnClickListener { presenter.scalePlus() }

        btnFuncs.setOnClickListener({
            view ->
                lvFuncs.visibility = View.VISIBLE
        })
    }
}
