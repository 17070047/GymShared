package com.carryondown.app.base;

import android.text.Html;
import android.text.Spanned;

public class Comment {
    private Spanned comment;
    public Comment(String comment){
        this.comment = Html.fromHtml( "<font color='#4A766E'>zhaizu: </font>" + comment );
    }

    public Spanned getComment() {
        return comment;
    }
}
