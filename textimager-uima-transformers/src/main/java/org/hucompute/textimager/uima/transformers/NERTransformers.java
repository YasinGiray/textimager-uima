package org.hucompute.textimager.uima.transformers;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.Type;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.dkpro.core.api.resources.MappingProvider;

import de.tudarmstadt.ukp.dkpro.core.api.ner.type.NamedEntity;
import jep.JepException;

public class NERTransformers extends BaseTransformers {

	
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		super.initialize(aContext);

	}
	

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		//		super.process(aJCas);
		HashMap<String, Object>  json = buildJSON(aJCas);
		try {
			interp.set("lang", aJCas.getDocumentLanguage());
			interp.set("words",json.get("words"));
			interp.set("spaces",json.get("spaces"));

			interp.exec("nlp = pipeline('ner')");
			interp.exec("nlp = pipeline('ner')");

		} catch (JepException e) {

		}
	}
}