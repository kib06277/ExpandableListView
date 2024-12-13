package com.funshow.expandablelistview

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView

//自訂義展開數據
class MyExpandableListViewAdapter(
    private val groupData: List<String> , //父類別數據
    private val childrenData: List<List<String>> //子類別數據
) : BaseExpandableListAdapter() {
    private var context: Context? = null

    inner class GroupViewHolder {
        var textView: TextView? = null
    }

    inner class ChildViewHolder {
        var textView: TextView? = null
    }

    //獲得父別組數
    override fun getGroupCount(): Int {
        return groupData.size
    }

    //獲得子類別組數
    override fun getChildrenCount(groupPosition: Int): Int {
        return childrenData[groupPosition].size
    }

    //獲得指定父類別數據
    override fun getGroup(groupPosition: Int): String {
        return groupData[groupPosition]
    }

    //獲得指定子類別數據
    override fun getChild(groupPosition: Int, childPosition: Int): String {
        return childrenData[groupPosition][childPosition]
    }

    //獲得父類別 ID
    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    //獲得子類別 ID
    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    //id是否固定，即資料變化後，資料的id是否改變
    override fun hasStableIds(): Boolean {
        return false
    }

    //綁定群組佈局和群組數據
    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        context = parent.context
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.simple_list_item_1, parent, false)

        val groupViewHolder: GroupViewHolder
        if (convertView == null) {
            groupViewHolder = GroupViewHolder()
            groupViewHolder.textView = view.findViewById(R.id.text1)
            view.tag = groupViewHolder
        } else {
            groupViewHolder = view.tag as GroupViewHolder
        }

        groupViewHolder.textView!!.text = groupData[groupPosition]
        return view
    }


    //綁定子佈局和子資料
    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.simple_list_item_1, parent, false)

        val childViewHolder: ChildViewHolder
        if (convertView == null) {
            childViewHolder = ChildViewHolder()
            childViewHolder.textView = view.findViewById(R.id.text1)
            view.tag = childViewHolder
        } else {
            childViewHolder = view.tag as ChildViewHolder
        }

        childViewHolder.textView!!.text = childrenData[groupPosition][childPosition]
        return view
    }


    //子資料是否可選擇
    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}
