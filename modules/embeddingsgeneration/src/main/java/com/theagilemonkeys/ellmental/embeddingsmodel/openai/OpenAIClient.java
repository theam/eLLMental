//package com.theagilemonkeys.ellmental.embeddingsmodel.openai
//
//import com.aallam.openai.api.http.Timeout
//import com.aallam.openai.client.*
//import kotlin.time.Duration.Companion.seconds
//
///**
// * # OpenAI client generator
// *
// * Wrapper to create an [OpenAI] client with default parameters for JVM compatibility
// *
// */
//@JvmOverloads
//fun OpenAIClient(
//        apiKey: String,
//        logging: LoggingConfig = LoggingConfig(),
//        timeout: Timeout = Timeout(socket = 30.seconds),
//        organization: String? = null,
//        headers: Map<String, String> = emptyMap(),
//        host: OpenAIHost = OpenAIHost.OpenAI,
//        proxy: ProxyConfig? = null,
//        retry: RetryStrategy = RetryStrategy(),
//        ): OpenAI {
//        return OpenAI(
//        token = apiKey,
//        logging = logging,
//        timeout = timeout,
//        organization = organization,
//        headers = headers,
//        host = host,
//        proxy = proxy,
//        retry = retry
//        )
//        }