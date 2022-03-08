package com.example.ncepu.provider;

import com.example.ncepu.ResourceTable;
import com.example.ncepu.Student.Query.ScorePdfAbility;
import com.example.ncepu.Student.Query.slice.ScorePdfAbilitySlice;
import com.example.ncepu.Utils.Grade;
import ohos.agp.components.*;
import ohos.app.Context;

import java.util.List;

public class ListItemProvider extends BaseItemProvider {

    private List<Grade> list;
    private Context context;

    public ListItemProvider(List<Grade> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        if (list != null && i >= 0 && i < list.size()){
            return list.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public Component getComponent(int i, Component component, ComponentContainer componentContainer) {
        final Component cpt;
        LayoutScatter layoutScatter = LayoutScatter.getInstance(context);
        if (component == null) {
            cpt = layoutScatter.parse(ResourceTable.Layout_pdf_list_item, null, false);
        } else {
            cpt = component;
        }
        Grade item = list.get(i);
        Text courseName = (Text) cpt.findComponentById(ResourceTable.Id_text_course_name);
        courseName.setText(item.getCourse_name());
        Text courseNature = (Text) cpt.findComponentById(ResourceTable.Id_text_course_nature);
        courseNature.setText(item.getCourse_nature());
        Text nq = (Text) cpt.findComponentById(ResourceTable.Id_text_year_semester);
        nq.setText(item.getXn() + "/" + item.getXq());
        Text credit = (Text) cpt.findComponentById(ResourceTable.Id_text_credit);
        credit.setText(item.getCredit());
        Text mark = (Text) cpt.findComponentById(ResourceTable.Id_text_grade);
        mark.setText(item.getMark());
        Text gpa = (Text) cpt.findComponentById(ResourceTable.Id_text_gpa);
        gpa.setText(item.getGpa());
        return cpt;
    }
}
