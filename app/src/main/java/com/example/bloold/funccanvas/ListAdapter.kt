package com.example.bloold.funccanvas

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.BaseAdapter
import android.widget.TextView

/**
 * Created by bloold on 14.09.17.
 */
class ListAdapter(private val listFunc: HashMap<String, (Double) -> Double>, private val context: Context) : BaseAdapter(){

    override fun getView(index: Int, view: View?, parent: ViewGroup?): View {
        var funcView = view

        if(view == null){
            funcView = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.item_func, parent, false)
        }

        funcView?.findViewById<TextView>(R.id.tvFuncName)?.text = getItemsKeys().toList().get(index)
        funcView?.tag = getItemsKeys().toList().get(index)

        funcView?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View?) {
                (context as MainActivity).funcView.func = listFunc.get(view?.tag.toString())!!
                (view?.parent as View).visibility = View.INVISIBLE
            }
        })

        return funcView!!
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    fun getItemsKeys(): MutableSet<String> {
        return listFunc.keys
    }

    override fun getItemId(p0: Int): Long {
        return  p0.toLong()
    }

    override fun getCount(): Int {
        return listFunc.size
    }

}