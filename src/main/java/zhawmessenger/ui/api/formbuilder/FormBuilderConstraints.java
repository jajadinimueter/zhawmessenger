package zhawmessenger.ui.api.formbuilder;

/**
 */
public class FormBuilderConstraints {

    public enum Align {
        LEFT, RIGHT
    }

    public Align align = null;

    public FormBuilderConstraints() {
    }

    public FormBuilderConstraints(Align align) {
        this.align = align;
    }
}
