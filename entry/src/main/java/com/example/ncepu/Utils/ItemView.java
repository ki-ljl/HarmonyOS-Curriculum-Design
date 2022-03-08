package com.example.ncepu.Utils;

import com.example.ncepu.ResourceTable;
import ohos.agp.components.*;
import ohos.app.Context;
import ohos.global.resource.solidxml.TypedAttribute;

public class ItemView extends DirectionalLayout {

    private LayoutScatter layoutScatter;
    public ItemView(Context context) {
        super(context);
    }

    public ItemView(Context context, AttrSet attrSet) {
        super(context, attrSet);
    }

    public ItemView(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);
        layoutScatter = LayoutScatter.getInstance(context);
        DependentLayout dependentLayout = (DependentLayout) layoutScatter.parse(ResourceTable.Layout_item_view_layout, null, false);
        addComponent(dependentLayout);
//        TypedAttribute ta = context.getPattern(ResourceTable.G);
    }
}
