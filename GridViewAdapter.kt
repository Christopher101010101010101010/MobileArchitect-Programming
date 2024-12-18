package com.example.christopherandersonprojecttwooption2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.w3c.dom.Text


data class GridViewModule(val anEventTitle: String, val anEventDateTime: String, val anEventDescription: String)


internal class GridViewAdapter(val anEventList: List<GridViewModule>, val context: Context) :
BaseAdapter(){

    var layoutInflater1: LayoutInflater? = null
    lateinit var textViewEventTitle : TextView
    lateinit var textViewEventDateTime : TextView
    lateinit var textViewEventDescript : TextView

    override fun getCount() : Int{
        return anEventList.size
    }

    override fun getItemId(position: Int): Long {
        return (position % anEventList.size) as Long
    }

    override fun getItem(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?) : View?{
        var convertView = convertView

        if(layoutInflater1 == null){
            layoutInflater1 = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        if(convertView == null){
            convertView = layoutInflater1!!.inflate(R.layout.gridview_item, null)
        }

        textViewEventTitle = convertView!!.findViewById(R.id.idTxtVwEventTitle)
        textViewEventDescript = convertView!!.findViewById(R.id.idTxtVwEventDescript)
        textViewEventDateTime = convertView!!.findViewById(R.id.idTxtVwEventTimeDate)

        textViewEventTitle.setText(anEventList.get(position).anEventTitle)
        textViewEventDescript.setText(anEventList.get(position).anEventDescription)
        textViewEventDateTime.setText(anEventList.get(position).anEventDateTime)

        return convertView

    }

}