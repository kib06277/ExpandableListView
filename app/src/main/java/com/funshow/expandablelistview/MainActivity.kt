package com.funshow.expandablelistview

import android.os.Bundle
import android.util.Log
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var list: ExpandableListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            list = findViewById(R.id.list)
            //父類別數據
            val groupData: MutableList<String> = ArrayList()
            groupData.add("小學")
            groupData.add("國中")
            groupData.add("高中")
            //父類別第一個子類別數據
            val childrenData: MutableList<List<String>> = ArrayList()
            val child1Data: MutableList<String> = ArrayList()
            child1Data.add("小學一年級")
            child1Data.add("小學二年級")
            child1Data.add("小學三年級")
            child1Data.add("小學四年級")
            child1Data.add("小學五年級")
            child1Data.add("小學六年級")
            //父類別第二個子類別數據
            val child2Data: MutableList<String> = ArrayList()
            child2Data.add("國中一年級")
            child2Data.add("國中二年級")
            child2Data.add("國中三年級")
            //父類別第三個子類別數據
            val child3Data: MutableList<String> = ArrayList()
            child3Data.add("高中一年級")
            child3Data.add("高中二年級")
            child3Data.add("高中三年級")

            //將子類別加入父類別
            childrenData.add(child1Data)
            childrenData.add(child2Data)
            childrenData.add(child3Data)

            //傳入數據
            val adapter: MyExpandableListViewAdapter = MyExpandableListViewAdapter(groupData, childrenData)
            list.setAdapter(adapter)
            list!!.setOnGroupClickListener { parent, v, groupPosition, id ->
                Toast.makeText(this@MainActivity, "點選" + adapter.getGroup(groupPosition), Toast.LENGTH_SHORT).show()
                false
            }
            list!!.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
                Toast.makeText(this@MainActivity, "點選" + adapter.getChild(groupPosition, childPosition), Toast.LENGTH_SHORT).show()
                true
            }
        } catch ( e : Exception) {
            Log.i("FunshowError" , "MainActivity e = $e")
        }
    }
}
