package jp.curigeo.codeful.sourceViewer;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * TODO: document your custom view class.
 */
public class SourceCodeView extends TextView {

    String fileType = "";
    PrettifyHighlighter highlighter = new PrettifyHighlighter();

    public SourceCodeView(Context context) {
        super(context);
    }

    public SourceCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SourceCodeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 言語名などのハイライトするためのソースの種類
     * @param sourcetype "java", "c"など
     */
    public void setFileType(String sourcetype) {
        fileType = sourcetype;
    }

    /**
     * ハイライトしたいソースコード文字列を登録
     * @param sourcecode ソースコード本体
     */
    public void setSourceCode(String sourcecode) {
        if (fileType == null || fileType.isEmpty()) {
            setText(sourcecode);
        } else {
            String highlightedStr = highlighter.highlight(fileType, sourcecode);
            setText(Html.fromHtml(highlightedStr));
        }
    }

    /**
     * オーバーライドして全部のっとろうかと考えたが面倒そうなので保留中。
     */
//    @Override
//    public void setText(CharSequence str, BufferType type) {
//        super.setText(str, type);
//    }
}
