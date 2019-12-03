package ru.amm.fileexplorer.server.data;

public enum MatcherType {

    PartialMatcher("Partial") {
        @Override
        public FileMatcher getMatcher(String match) {
            return new NamePartialMatcher(match);
        }
    },
    FullMatcher("Full") {
        @Override
        public FileMatcher getMatcher(String match) {
            return new NameMatcher(match);
        }
    },
    NoOpMatcher("NoOp") {
        @Override
        public FileMatcher getMatcher(String match) {
            return new NoOpMatcher();
        }
    };

    MatcherType(String mType) {
    }

    public abstract FileMatcher getMatcher(String match);
}
