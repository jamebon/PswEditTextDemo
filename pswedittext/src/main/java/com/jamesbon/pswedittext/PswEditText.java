package com.jamesbon.pswedittext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

public class PswEditText extends EditText {
    private int itemCount = 6; //格子的数量，默认6个
    private Paint linePaint = new Paint();
    private Paint pointPaint = new Paint();
    private OnPswEditListener onPswEditListener;
    int lineColor = Color.parseColor("#707070");
    private int lineWidth = 1;
    int pointColor = Color.parseColor("#343745");
    private int pointWidth = 10;

    public PswEditText(Context context) {
        super(context);
        init();
    }

    public PswEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PswEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setHintTextColor(Color.TRANSPARENT);
        setTextColor(Color.TRANSPARENT);
        setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        setCursorVisible(false);
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(itemCount)});
        setKeyListener(DigitsKeyListener.getInstance("0123456789"));
        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(lineWidth);
        linePaint.setAntiAlias(true);
        pointPaint.setAntiAlias(true);
        pointPaint.setStyle(Paint.Style.FILL);
        pointPaint.setColor(pointColor);
        pointPaint.setStrokeWidth(pointWidth);
        addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == getItemCount()) {
                    if (onPswEditListener != null) {
                        onPswEditListener.onDone(s);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public PswEditText setLineColor(int lineColor) {
        this.lineColor = lineColor;
        return this;
    }

    public PswEditText setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
        return this;
    }

    public PswEditText setPointColor(int pointColor) {
        this.pointColor = pointColor;
        return this;
    }

    public PswEditText setPointWidth(int pointWidth) {
        this.pointWidth = pointWidth;
        return this;
    }

    public void updateSetting() {
        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(lineWidth);
        pointPaint.setColor(pointColor);
        pointPaint.setStrokeWidth(pointWidth);
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(itemCount)});
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int totalWidth = getMeasuredWidth();
        int itemWidth = totalWidth / this.itemCount;
        int itemHeight = getMeasuredHeight() - getPaddingBottom(); //每一个格子都是正方形
        //画分割线
        for (int i = 1; i < this.itemCount; i++) {
            int lineStartPosX = i * itemWidth;
            canvas.drawLine(lineStartPosX, getPaddingTop(), lineStartPosX, itemHeight, linePaint);
        }
        //画点
        int charCount = getText() == null ? 0 : getText().toString().trim().length();
        int itemCenterX = itemWidth / 2;
        int itemCenterY = getMeasuredHeight() / 2;
        for (int i = 0; i < charCount; i++) {
            int offsetX = i * itemWidth;
            canvas.drawCircle(offsetX + itemCenterX, itemCenterY, 10, pointPaint);
        }
    }

    public PswEditText setOnPswEditListener(OnPswEditListener onPswEditListener) {
        this.onPswEditListener = onPswEditListener;
        return this;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int itemWidth = View.MeasureSpec.getSize(widthMeasureSpec) / this.itemCount;
        int newHeightSpec = View.MeasureSpec.makeMeasureSpec(itemWidth, View.MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, newHeightSpec);
    }


    public int getItemCount() {
        return itemCount;
    }

    public PswEditText setItemCount(int itemCount) {
        this.itemCount = itemCount;
        return this;
    }

    public interface OnPswEditListener {
        void onDone(CharSequence text);
    }


}
