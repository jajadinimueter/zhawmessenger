package zhawmessenger.ui.impl.components;

/**
 * The Logic searchTo the suggestor is extracted for better testability
 */
public class SuggestorLogic<T> {

//    private final SuggesterValueExtractor<T> extractor;
//    private final List<Finder<String,T>> finders;

    class SearchRange {
        private final int searchFrom;
        private final int searchTo;
        private final int replaceFrom;
        private final int replaceTo;

        public SearchRange(int searchFrom, int searchTo,
                           int replaceFrom, int replaceTo) {
            this.searchFrom = searchFrom;
            this.searchTo = searchTo;
            this.replaceFrom = replaceFrom;
            this.replaceTo = replaceTo;
        }

        public int getReplaceFrom() {
            return replaceFrom;
        }

        public int getReplaceTo() {
            return replaceTo;
        }

        public int getSearchFrom() {
            return searchFrom;
        }

        public int getSearchTo() {
            return searchTo;
        }
    }

//    public SuggestorLogic(SuggesterValueExtractor<T> extractor,
//                          List<Finder<String, T>> finders) {
//        this.extractor = extractor;
//        this.finders = finders;
//    }

    public SearchRange getSearchRange(String text, int caretPosition) {
        int startPos;
        int searchFrom = -1, searchTo = -1;
        int replaceFrom = -1, replaceTo = -1;

        if (caretPosition > text.length()) {
            caretPosition = text.length();
        }

        if (caretPosition == 0) {
            startPos = 0;
        } else {
            startPos = caretPosition - 1;
        }

        if ( text.length() > 0 ) {
            for (int i = startPos; i >= 0; i--) {
                char x = text.charAt(i);
                if (x == ',') {
                    searchFrom = i + 1;
                    replaceFrom = i + 1;
                    break;
                } else if (x == '<') {
                    searchFrom = i + 1;
                    replaceFrom = i;
                    break;
                }
            }

            for (int i = startPos + 1; i < text.length(); i++ ) {
                char x = text.charAt(i);
                if (x == ',') {
                    searchTo = i - 1;
                    replaceFrom = i - 1;
                    break;
                } else if (x == '>') {
                    searchTo = i - 1;
                    replaceFrom = i;
                    break;
                }

            }
        }

        if (replaceFrom == -1) {
            replaceFrom = 0;
        }

        if (replaceTo == -1) {
            replaceTo = text.length();
        }

        if (searchFrom == -1) {
            searchFrom = 0;
        }

        if (searchTo == -1) {
            searchTo = text.length();
        }

        if (text.length() > replaceTo) {
            replaceTo = replaceTo + 1;
        }

        return new SearchRange(searchFrom, searchTo, replaceFrom, replaceTo);
    }
}
