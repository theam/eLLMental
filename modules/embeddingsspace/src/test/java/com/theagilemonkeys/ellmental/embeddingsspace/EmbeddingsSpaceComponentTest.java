package com.theagilemonkeys.ellmental.embeddingsspace;

import com.theagilemonkeys.ellmental.core.schema.Embedding;
import com.theagilemonkeys.ellmental.embeddingsgeneration.EmbeddingsGenerationModel;
import com.theagilemonkeys.ellmental.embeddingsstore.EmbeddingsStore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmbeddingsSpaceComponentTest {
    @Mock
    private EmbeddingsGenerationModel embeddingsGenerationModel;
    @Mock
    private EmbeddingsStore embeddingsStore;
    @InjectMocks
    private EmbeddingsSpaceComponent embeddingsSpaceComponent;
    @Captor
    private ArgumentCaptor<String> sampleTextCaptor;

    private final String sampleText = "sample text";
    private final UUID embeddingId = UUID.randomUUID();
    private final Embedding embeddingMock = new Embedding(embeddingId, List.of(1.0, 2.0, 3.0), null);

    @Test
    public void testSaveEmbedding() {
        when(embeddingsGenerationModel.generateEmbedding(sampleText)).thenReturn(embeddingMock);

        embeddingsSpaceComponent.save(sampleText);

        verify(embeddingsGenerationModel).generateEmbedding(sampleTextCaptor.capture());
        String capturedSampleText = sampleTextCaptor.getValue();
        assertEquals(sampleText, capturedSampleText);

        verify(embeddingsStore).store(embeddingMock);

        verifyNoMoreInteractions(embeddingsGenerationModel, embeddingsStore);
    }

    @Test
    public void testMostSimilarEmbeddings() {
        int limit = 1;
        Embedding similarEmbeddingMock = new Embedding(UUID.randomUUID(), List.of(4.0, 5.0, 6.0), null);

        when(embeddingsStore.similaritySearch(embeddingMock, limit)).thenReturn(List.of(similarEmbeddingMock));

        List<Embedding> embeddings = embeddingsSpaceComponent.mostSimilarEmbeddings(embeddingMock, limit);

        assertEquals(embeddings.get(0), similarEmbeddingMock);

        verifyNoMoreInteractions(embeddingsGenerationModel, embeddingsStore);
    }

    @Test
    public void testMostSimilarEmbeddingsWhenUsingString() {
        int limit = 1;
        Embedding similarEmbeddingMock = new Embedding(UUID.randomUUID(), List.of(4.0, 5.0, 6.0), null);

        when(embeddingsGenerationModel.generateEmbedding(sampleText)).thenReturn(embeddingMock);
        when(embeddingsStore.similaritySearch(embeddingMock, limit)).thenReturn(List.of(similarEmbeddingMock));

        List<Embedding> embeddings = embeddingsSpaceComponent.mostSimilarEmbeddings(sampleText, limit);

        assertEquals(embeddings.get(0), similarEmbeddingMock);

        verify(embeddingsGenerationModel).generateEmbedding(sampleTextCaptor.capture());
        String capturedSampleText = sampleTextCaptor.getValue();
        assertEquals(sampleText, capturedSampleText);

        verifyNoMoreInteractions(embeddingsGenerationModel, embeddingsStore);
    }

    @Test
    public void testGetEmbedding() {
        when(embeddingsStore.get(embeddingId)).thenReturn(embeddingMock);

        Embedding embedding = embeddingsSpaceComponent.get(embeddingId);

        assertEquals(embeddingMock, embedding);

        verifyNoMoreInteractions(embeddingsGenerationModel, embeddingsStore);
    }

    @Test
    public void testDeleteEmbedding() {
        embeddingsSpaceComponent.delete(embeddingId);

        verify(embeddingsStore).delete(embeddingId);

        verifyNoMoreInteractions(embeddingsGenerationModel, embeddingsStore);
    }
}
