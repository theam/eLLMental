//package com.theagilemonkeys.ellmental.embeddingsmodel
//
//import com.theagilemonkeys.ellmental.core.schema.Embedding
//
///**
// * # Embeddings Model
// *
// * An Embeddings Model allows embedding text into a vector space. It is parameterized via the [Params] type
// * in order to allow different implementations to have additional parameters apart from the text to embed.
// *
// * @param Params The type of the parameters used by the model.
// */
//interface EmbeddingsModel<Params> {
//
//    /**
//     * Embeds the given text into a vector space.
//     *
//     * @param text The text to embed.
//     * @param params Additional parameters to use when embedding the text (implementation specific).
//     * @return An [Embedding] object containing the embedding vector.
//     */
//    suspend fun embed(text: String, params: Params? = null): Embedding
//}
