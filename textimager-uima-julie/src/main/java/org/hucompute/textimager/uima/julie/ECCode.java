package org.hucompute.textimager.uima.julie;

public class ECCode extends JulieBase {
    /**
     * Tagger address.
     * @return endpoint
     */
    @Override
    protected String getRestRoute() {
        return "/eccode";
    }
}
