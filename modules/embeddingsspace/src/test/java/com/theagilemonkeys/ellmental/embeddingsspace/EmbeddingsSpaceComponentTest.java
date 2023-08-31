package com.theagilemonkeys.ellmental.embeddingsspace;

import com.theagilemonkeys.ellmental.core.schema.Embedding;
import com.theagilemonkeys.ellmental.embeddingsgeneration.EmbeddingsGenerationModel;
import com.theagilemonkeys.ellmental.embeddingsstore.EmbeddingsStore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @Test
    public void testSaveEmbedding() {
        Embedding embeddingMock = new Embedding(UUID.randomUUID(), List.of(1.0, 2.0, 3.0), null);
        when(embeddingsGenerationModel.generateEmbedding(sampleText)).thenReturn(embeddingMock);
        //doNothing().when(embeddingsStore).store(embeddingMock);
        embeddingsSpaceComponent.save(sampleText);

        verify(embeddingsGenerationModel).generateEmbedding(sampleTextCaptor.capture());
        String capturedSampleText = sampleTextCaptor.getValue();
        assertEquals(sampleText, capturedSampleText);

        verify(embeddingsStore).store(embeddingMock);

        verifyNoMoreInteractions(embeddingsGenerationModel, embeddingsStore);
    }

    @Test
    public void testMostSimilarEmbeddings() {
        assertTrue(true);
    }

    @Test
    public void testMostSimilarEmbeddingsWhenUsingString() {
        assertTrue(true);
    }

    @Test
    public void testGetEmbedding() {
        assertTrue(true);
    }

    @Test
    public void testDeleteEmbedding() {
        assertTrue(true);
    }
}