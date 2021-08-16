import Reader.JsonReader;
import org.apache.uima.UIMAException;
import org.apache.uima.UimaContext;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.hucompute.textimager.uima.base.DockerRestAnnotator;
import org.hucompute.textimager.uima.base.RestAnnotator;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import java.io.IOException;

public class LinnaeusSpecies extends DockerRestAnnotator {
    /**
     * Tagger address.
     * @return endpoint
     */
    @Override
    protected String getRestRoute() {
        return "/linnaeusSpecies";
    }
    /**
     * Docker image name.
     * @return name
     */
    @Override
    protected String getDefaultDockerImage() {
        return "textimager-juli-api";
    }
    /**
     * Docker image tag.
     * @return tag
     */
    @Override
    protected String getDefaultDockerImageTag() {
        return "1.3";
    }

    @Override
    protected int getDefaultDockerPort() {
        return 8080;
    }
    @Override
    public void initialize(UimaContext aContext) throws ResourceInitializationException
    {
        super.initialize(aContext);
    }

    /**
     * Convert jCas to Json.
     * @return JSON
     */
    @Override
    protected JSONObject buildJSON(JCas aJCas) throws IOException, SAXException {

        JsonReader reader = new JsonReader();
        return reader.CasToJson(aJCas);
    }
    /**
     * Read Json and update jCas.
     * @param aJCas
     */
    @Override
    protected void updateCAS(JCas aJCas, JSONObject jsonResult) throws UIMAException, IOException, SAXException {

        JsonReader reader = new JsonReader();
        reader.UpdateJsonToCas(jsonResult, aJCas);

        /*for (Token token: JCasUtil.select(aJCas, Token.class))
        {
            de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token dtoken = new de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token(aJCas, token.getBegin(), token.getEnd());
            POS postag = new POS(aJCas, token.getPosTag(0).getBegin(), token.getPosTag(0).getEnd());
            postag.setPosValue(token.getPosTag(0).getValue());
            dtoken.setPos(postag);
            dtoken.addToIndexes();
        }*/

    }
}