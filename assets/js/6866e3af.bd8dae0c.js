"use strict";
(self["webpackChunkellmental"] = self["webpackChunkellmental"] || []).push([[888],{

/***/ 3905:
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   Zo: () => (/* binding */ MDXProvider),
/* harmony export */   kt: () => (/* binding */ createElement)
/* harmony export */ });
/* unused harmony exports MDXContext, useMDXComponents, withMDXComponents */
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(7294);


function _defineProperty(obj, key, value) {
  if (key in obj) {
    Object.defineProperty(obj, key, {
      value: value,
      enumerable: true,
      configurable: true,
      writable: true
    });
  } else {
    obj[key] = value;
  }

  return obj;
}

function _extends() {
  _extends = Object.assign || function (target) {
    for (var i = 1; i < arguments.length; i++) {
      var source = arguments[i];

      for (var key in source) {
        if (Object.prototype.hasOwnProperty.call(source, key)) {
          target[key] = source[key];
        }
      }
    }

    return target;
  };

  return _extends.apply(this, arguments);
}

function ownKeys(object, enumerableOnly) {
  var keys = Object.keys(object);

  if (Object.getOwnPropertySymbols) {
    var symbols = Object.getOwnPropertySymbols(object);
    if (enumerableOnly) symbols = symbols.filter(function (sym) {
      return Object.getOwnPropertyDescriptor(object, sym).enumerable;
    });
    keys.push.apply(keys, symbols);
  }

  return keys;
}

function _objectSpread2(target) {
  for (var i = 1; i < arguments.length; i++) {
    var source = arguments[i] != null ? arguments[i] : {};

    if (i % 2) {
      ownKeys(Object(source), true).forEach(function (key) {
        _defineProperty(target, key, source[key]);
      });
    } else if (Object.getOwnPropertyDescriptors) {
      Object.defineProperties(target, Object.getOwnPropertyDescriptors(source));
    } else {
      ownKeys(Object(source)).forEach(function (key) {
        Object.defineProperty(target, key, Object.getOwnPropertyDescriptor(source, key));
      });
    }
  }

  return target;
}

function _objectWithoutPropertiesLoose(source, excluded) {
  if (source == null) return {};
  var target = {};
  var sourceKeys = Object.keys(source);
  var key, i;

  for (i = 0; i < sourceKeys.length; i++) {
    key = sourceKeys[i];
    if (excluded.indexOf(key) >= 0) continue;
    target[key] = source[key];
  }

  return target;
}

function _objectWithoutProperties(source, excluded) {
  if (source == null) return {};

  var target = _objectWithoutPropertiesLoose(source, excluded);

  var key, i;

  if (Object.getOwnPropertySymbols) {
    var sourceSymbolKeys = Object.getOwnPropertySymbols(source);

    for (i = 0; i < sourceSymbolKeys.length; i++) {
      key = sourceSymbolKeys[i];
      if (excluded.indexOf(key) >= 0) continue;
      if (!Object.prototype.propertyIsEnumerable.call(source, key)) continue;
      target[key] = source[key];
    }
  }

  return target;
}

var isFunction = function isFunction(obj) {
  return typeof obj === 'function';
};

var MDXContext = /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_0__.createContext({});
var withMDXComponents = function withMDXComponents(Component) {
  return function (props) {
    var allComponents = useMDXComponents(props.components);
    return /*#__PURE__*/React.createElement(Component, _extends({}, props, {
      components: allComponents
    }));
  };
};
var useMDXComponents = function useMDXComponents(components) {
  var contextComponents = react__WEBPACK_IMPORTED_MODULE_0__.useContext(MDXContext);
  var allComponents = contextComponents;

  if (components) {
    allComponents = isFunction(components) ? components(contextComponents) : _objectSpread2(_objectSpread2({}, contextComponents), components);
  }

  return allComponents;
};
var MDXProvider = function MDXProvider(props) {
  var allComponents = useMDXComponents(props.components);
  return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_0__.createElement(MDXContext.Provider, {
    value: allComponents
  }, props.children);
};

var TYPE_PROP_NAME = 'mdxType';
var DEFAULTS = {
  inlineCode: 'code',
  wrapper: function wrapper(_ref) {
    var children = _ref.children;
    return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_0__.createElement(react__WEBPACK_IMPORTED_MODULE_0__.Fragment, {}, children);
  }
};
var MDXCreateElement = /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_0__.forwardRef(function (props, ref) {
  var propComponents = props.components,
      mdxType = props.mdxType,
      originalType = props.originalType,
      parentName = props.parentName,
      etc = _objectWithoutProperties(props, ["components", "mdxType", "originalType", "parentName"]);

  var components = useMDXComponents(propComponents);
  var type = mdxType;
  var Component = components["".concat(parentName, ".").concat(type)] || components[type] || DEFAULTS[type] || originalType;

  if (propComponents) {
    return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_0__.createElement(Component, _objectSpread2(_objectSpread2({
      ref: ref
    }, etc), {}, {
      components: propComponents
    }));
  }

  return /*#__PURE__*/react__WEBPACK_IMPORTED_MODULE_0__.createElement(Component, _objectSpread2({
    ref: ref
  }, etc));
});
MDXCreateElement.displayName = 'MDXCreateElement';
function createElement (type, props) {
  var args = arguments;
  var mdxType = props && props.mdxType;

  if (typeof type === 'string' || mdxType) {
    var argsLength = args.length;
    var createElementArgArray = new Array(argsLength);
    createElementArgArray[0] = MDXCreateElement;
    var newProps = {};

    for (var key in props) {
      if (hasOwnProperty.call(props, key)) {
        newProps[key] = props[key];
      }
    }

    newProps.originalType = type;
    newProps[TYPE_PROP_NAME] = typeof type === 'string' ? type : mdxType;
    createElementArgArray[1] = newProps;

    for (var i = 2; i < argsLength; i++) {
      createElementArgArray[i] = args[i];
    }

    return react__WEBPACK_IMPORTED_MODULE_0__.createElement.apply(null, createElementArgArray);
  }

  return react__WEBPACK_IMPORTED_MODULE_0__.createElement.apply(null, args);
}




/***/ }),

/***/ 9966:
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   assets: () => (/* binding */ assets),
/* harmony export */   contentTitle: () => (/* binding */ contentTitle),
/* harmony export */   "default": () => (/* binding */ MDXContent),
/* harmony export */   frontMatter: () => (/* binding */ frontMatter),
/* harmony export */   metadata: () => (/* binding */ metadata),
/* harmony export */   toc: () => (/* binding */ toc)
/* harmony export */ });
/* harmony import */ var _home_runner_work_eLLMental_eLLMental_docs_site_node_modules_babel_runtime_helpers_esm_extends_js__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(7462);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(7294);
/* harmony import */ var _mdx_js_react__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(3905);
/* @jsxRuntime classic */ /* @jsx mdx */ /* @jsxFrag React.Fragment */const frontMatter={slug:'/getting-started'};const contentTitle='Getting started';const metadata={"unversionedId":"getting_started","id":"getting_started","title":"Getting started","description":"eLLMental is a library designed for building AI-powered applications written in Java, and it offers production-ready components that can be used right away in your current JVM projects. In this guide, we will showcase how to use the EmbeddingsSpaceComponent to find relevant text based on a query.","source":"@site/docs/02_getting_started.md","sourceDirName":".","slug":"/getting-started","permalink":"/getting-started","draft":false,"tags":[],"version":"current","lastUpdatedBy":"Juan José Rodríguez López","lastUpdatedAt":1692269003,"formattedLastUpdatedAt":"Aug 17, 2023","sidebarPosition":2,"frontMatter":{"slug":"/getting-started"},"sidebar":"docs","previous":{"title":"Introduction","permalink":"/"},"next":{"title":"Core Abstractions","permalink":"/components/core_abstractions"}};const assets={};const toc=[{value:'Step 1: Add the eLLMental dependencies using JitPack',id:'step-1-add-the-ellmental-dependencies-using-jitpack',level:2},{value:'Step 2: Initializing the EmbeddingsSpaceComponent',id:'step-2-initializing-the-embeddingsspacecomponent',level:2},{value:'Step 3: Running the example',id:'step-3-running-the-example',level:2},{value:'Next steps',id:'next-steps',level:2}];const layoutProps={toc};const MDXLayout="wrapper";function MDXContent(_ref){let{components,...props}=_ref;return (0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)(MDXLayout,(0,_home_runner_work_eLLMental_eLLMental_docs_site_node_modules_babel_runtime_helpers_esm_extends_js__WEBPACK_IMPORTED_MODULE_2__/* ["default"] */ .Z)({},layoutProps,props,{components:components,mdxType:"MDXLayout"}),(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("h1",{"id":"getting-started"},`Getting started`),(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("p",null,`eLLMental is a library designed for building AI-powered applications written in Java, and it offers production-ready components that can be used right away in your current JVM projects. In this guide, we will showcase how to use the `,(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("inlineCode",{parentName:"p"},`EmbeddingsSpaceComponent`),` to find relevant text based on a query.`),(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("h2",{"id":"step-1-add-the-ellmental-dependencies-using-jitpack"},`Step 1: Add the eLLMental dependencies using `,(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("a",{parentName:"h2","href":"https://jitpack.io"},`JitPack`)),(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("p",null,`Incorporate the eLLMental dependencies into your `,(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("inlineCode",{parentName:"p"},`build.gradle`),` file.`),(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("p",null,`Here's a reference for you:`),(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("pre",null,(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("code",{parentName:"pre","className":"language-java"},`allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.theam:ellmental:main'
}
`)),(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("h2",{"id":"step-2-initializing-the-embeddingsspacecomponent"},`Step 2: Initializing the EmbeddingsSpaceComponent`),(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("p",null,`Before initializing the `,(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("inlineCode",{parentName:"p"},`EmbeddingsSpaceComponent`),`, set up the `,(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("inlineCode",{parentName:"p"},`OpenAIEmbeddingsModel`),` and `,(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("inlineCode",{parentName:"p"},`PineconeVectorStore`),`.`),(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("p",null,`Retrieve the required API tokens and configuration parameters following the `,(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("a",{parentName:"p","href":"https://docs.pinecone.io/docs/quickstart"},`PineCone quickstart guide`),` and `,(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("a",{parentName:"p","href":"https://platform.openai.com/docs/guides/production-best-practices/api-keys"},`OpenAI API keys guide`),`.`),(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("pre",null,(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("code",{parentName:"pre","className":"language-java"},`import com.theagilemonkeys.ellmental.embeddingsmodel.openai.OpenAIEmbeddingsModel;
import com.theagilemonkeys.ellmental.vectorstore.pinecone.PineconeVectorStore;

public OpenAIEmbeddingsModel embeddingsModel() {
    String openaiToken = System.getenv("OPEN_AI_API_KEY");
    if (openaiToken == null) {
        throw new RuntimeException("OPEN_AI_API_KEY environment variable is not set");
    }
    return new OpenAIEmbeddingsModel(openAIToken);
}

public PineconeVectorStore vectorStore() {
    String pineconeToken = System.getenv("PINECONE_API_KEY");
    if (pineconeToken == null) {
        throw new RuntimeException("PINECONE_API_KEY environment variable is not set");
    }
    String pineconeUrl = System.getenv("PINECONE_URL");
    if (pineconeUrl == null) {
        throw new RuntimeException("PINECONE_URL environment variable is not set");
    }
    String pineconeNamespace = System.getenv("PINECONE_NAMESPACE");  // optional parameter
    return new PineconeVectorStore(pineconeToken, pineconeUrl, pineconeNamespace);
}
`)),(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("p",null,`Now, initialize the `,(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("inlineCode",{parentName:"p"},`EmbeddingsSpaceComponent`),`:`),(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("pre",null,(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("code",{parentName:"pre","className":"language-java"},`import com.theagilemonkeys.ellmental.EmbeddingsSpaceComponent;

public EmbeddingsSpaceComponent initializeEmbeddingsSpace() {
    return new EmbeddingsSpaceComponent(embeddingsModel(), vectorStore());
}
`)),(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("h2",{"id":"step-3-running-the-example"},`Step 3: Running the example`),(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("p",null,`To run an example, you can write a simple main function:`),(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("pre",null,(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("code",{parentName:"pre","className":"language-java"},`public class MainApp {

    public static void main(String[] args) {
        EmbeddingsSpaceComponent embeddingsSpace = initializeEmbeddingsSpace();
        
        // Add some embedding samples to the embeddings space
        embeddingsSpace.add("Hello, eLLMental!", null);
        embeddingsSpace.add("Hello, world!", null);
        embeddingsSpace.add("Hi!", null);
        embeddingsSpace.add("Cats are cute", null);
        embeddingsSpace.add("Dogs are loyal", null);
        
        // Search similar embeddings
        List<Embedding> results = embeddingsSpace.mostSimilarEmbeddings("Greetings!", 3);
        for (Embedding embedding : results) {
            System.out.println(embedding.getText());
        }
    }
}
`)),(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("p",null,`Run the main function, and you should see the most similar texts to "Greetings!":`),(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("pre",null,(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("code",{parentName:"pre","className":"language-bash"},`$ ./gradlew run

> Task :run
Hello, eLLMental!
Hello, world!
Hi!
`)),(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("blockquote",null,(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("p",{parentName:"blockquote"},`Notice that the result outputs three entries because we specified the limit to be 3 in the `,(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("inlineCode",{parentName:"p"},`mostSimilarEmbeddings`),` function, but you can change this value to any number you want. Take into account that in the embeddings space, the database will calculate distances with every other embedding, so higher limits may return results that are not strictly similar to the query. Take into account that the list is ranked by similarity, so the first result is the most similar to the query and the latest is the least similar.`)),(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("h2",{"id":"next-steps"},`Next steps`),(0,_mdx_js_react__WEBPACK_IMPORTED_MODULE_1__/* .mdx */ .kt)("p",null,`Now that you've learned the basics, you can inlcude eLLMental in your own projects and start experimenting. Try to generate embeddings for a larger corpus of texts like HTML files extracted from a web scraper process, a series of blog posts from your database or a collection of tweets from your Twitter account`));};MDXContent.isMDXComponent=true;

/***/ })

}]);