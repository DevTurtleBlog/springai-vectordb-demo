package com.devturtle.springai.vectordbdemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAiEmbeddingController {
	
	@Autowired 
	private VectorStore vectorStore;
	
	
	@PostMapping("/ai/embeddings/add")
    public void add(@RequestBody List<String> docs) {
		List<Document> documents = new ArrayList<Document>();
		docs.forEach((el) -> { 
			documents.add(new Document(el)); 
		});
		vectorStore.add(documents);
    } 
	
		
	@GetMapping("ai/embeddings/search")
	public List<Document> search(@RequestParam(value = "query") String query) {
		
		return  vectorStore.similaritySearch(
					SearchRequest.defaults()
		                .withQuery(query)
		                .withSimilarityThreshold(0.5));
		
	}
	
	
}
