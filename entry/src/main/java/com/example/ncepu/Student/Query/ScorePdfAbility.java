package com.example.ncepu.Student.Query;

import com.example.ncepu.ResourceTable;
import com.example.ncepu.Student.Query.slice.ScorePdfAbilitySlice;
import com.example.ncepu.Utils.Grade;
import com.example.ncepu.provider.ListItemProvider;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.Image;
import ohos.agp.components.ListContainer;
import ohos.agp.components.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.ncepu.MainAbility.connectJWGL;

public class ScorePdfAbility extends Ability {

    private ListContainer listContainer;
    private Image back;
    private Text total_tv, major_tv, spec_tv, practice_tv, sch_tv, gpa_tv;
    private Text epu;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(ScorePdfAbilitySlice.class.getName());
        super.setUIContent(ResourceTable.Layout_ability_score_pdf);
        initViews();
    }

    private void initViews() {
        listContainer = (ListContainer) findComponentById(ResourceTable.Id_cj_list);
        back = (Image) findComponentById(ResourceTable.Id_ib_back_pdf);
        back.setClickedListener(component -> onBackPressed());
        //上方
        total_tv = (Text) findComponentById(ResourceTable.Id_text_total_credit);
        major_tv = (Text) findComponentById(ResourceTable.Id_text_major_credit);
        spec_tv = (Text) findComponentById(ResourceTable.Id_text_spec_credit);
        practice_tv = (Text) findComponentById(ResourceTable.Id_text_practice_credit);
        sch_tv = (Text) findComponentById(ResourceTable.Id_text_sch_credit);
        gpa_tv = (Text) findComponentById(ResourceTable.Id_text_avg);
        epu = (Text) findComponentById(ResourceTable.Id_pdf_info);

        final ArrayList<Grade>[] list = new ArrayList[]{new ArrayList()};
        Thread thread = new Thread(() -> {
            try {
                list[0] = connectJWGL.getStudentGrade("", "", "全部");
            } catch (Exception e) {
                e.printStackTrace();
            }
            //计算已获得总学分，其中包括必修、专选、实践、校选以及平均学分绩
            double total = 0, major = 0, spec = 0, practice = 0, sch = 0;
            double sum = 0, credits = 0;
            for(Grade grade : list[0]) {
                String credit = grade.getCredit();
                String nature = grade.getCourse_nature();
                String gpa = grade.getGpa();
                String gradeNature = grade.getGrade_nature();
                double temp = Double.parseDouble(credit);

                if(Double.parseDouble(gpa) < 1) {
                    continue;
                }
                total += temp;
                if(nature.equals("必修课")) {
                    major += temp;
                }else if(nature.equals("专选课")) {
                    spec += temp;
                }else if(nature.equals("实践课")) {
                    practice += temp;
                }else if(nature.equals("校选修课")) {
                    sch += temp;
                }
                double mark = Double.parseDouble(gpa) * 10 + 50;
                sum += temp * mark;
                credits += temp;
            }
            double avg = sum / credits;
            DecimalFormat df = new DecimalFormat("#.00");
            String avgs = df.format(avg);
//            SpannableStringBuilder builder = new SpannableStringBuilder("总学分\n" + total);
//            builder.setSpan(new ForegroundColorSpan(Color.RED), 4, builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            total_tv.setText("总学分\n" + total);
//            builder = new SpannableStringBuilder("必修\n" + major);
//            builder.setSpan(new ForegroundColorSpan(Color.RED), 3, builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            major_tv.setText("必修\n" + major);
//            builder = new SpannableStringBuilder("专选\n" + spec);
//            builder.setSpan(new ForegroundColorSpan(Color.RED), 3, builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spec_tv.setText("专选\n" + spec);
//            builder = new SpannableStringBuilder("实践\n" + practice);
//            builder.setSpan(new ForegroundColorSpan(Color.RED), 3, builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            practice_tv.setText("实践\n" + practice);
//            builder = new SpannableStringBuilder("校选\n" + sch);
//            builder.setSpan(new ForegroundColorSpan(Color.RED), 3, builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            sch_tv.setText("校选\n" + sch);
//            builder = new SpannableStringBuilder("平均学分绩\n" + avgs);
//            builder.setSpan(new ForegroundColorSpan(Color.RED), 6, builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            gpa_tv.setText("平均学分绩\n" + avgs);
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ListItemProvider provider = new ListItemProvider(list[0], this);
        listContainer.setItemProvider(provider);
    }
}
