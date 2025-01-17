package org.hucompute.textimager.uima.julie;

import de.julielab.jcore.types.EntityMention;
import de.julielab.jcore.types.Token;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.junit.Test;

import java.io.IOException;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.junit.Assert.assertArrayEquals;
/**
 * Jnet
 *
 * @date 13.08.2021
 *
 * @author Grzegorz Siwiecki, Chieh Kang
 * @version 1.1
 *
 * This class provide jnet test case */
public class JnetTest {
    /**
     * Test for simple english text.
     * @throws UIMAException
     */
    @Test
    public void jnetTestEn() throws IOException, UIMAException {

        JCas jCas = JCasFactory.createText("Identification of cDNAs encoding two human alpha class glutathione transferases ( GSTA3 and GSTA4 ) and the heterologous expression of GSTA4E - 4 .");
        jCas.setDocumentLanguage("en");

        //test zwecke
        //AnalysisEngineDescription segmenter = createEngineDescription(LanguageToolSegmenter.class);
        //SimplePipeline.runPipeline(jCas, segmenter);

        //AnalysisEngineDescription engine = createEngineDescription(Jnet.class);
        AnalysisEngineDescription engine = createEngineDescription(Jnet.class);
        SimplePipeline.runPipeline(jCas, engine);

        String[] casEntityMention = (String[]) JCasUtil.select(jCas, EntityMention.class).stream().map(a -> a.getCoveredText()+ " - "+a.getSpecificType()).toArray(String[]::new);

        String[] testEntityMention = new String[] {
                "alpha class glutathione transferases - gene-protein","GSTA3 - gene-rna","GSTA4 - gene-rna","GSTA4E - 4 - gene-rna"
        };

        assertArrayEquals(testEntityMention, casEntityMention);

    }
}
