"use strict";
(self["webpackChunkellmental"] = self["webpackChunkellmental"] || []).push([[728],{

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

/***/ 3391:
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

// ESM COMPAT FLAG
__webpack_require__.r(__webpack_exports__);

// EXPORTS
__webpack_require__.d(__webpack_exports__, {
  assets: () => (/* binding */ assets),
  contentTitle: () => (/* binding */ contributing_contentTitle),
  "default": () => (/* binding */ contributing_MDXContent),
  frontMatter: () => (/* binding */ contributing_frontMatter),
  metadata: () => (/* binding */ metadata),
  toc: () => (/* binding */ contributing_toc)
});

// EXTERNAL MODULE: ./node_modules/@babel/runtime/helpers/esm/extends.js
var esm_extends = __webpack_require__(7462);
// EXTERNAL MODULE: ./node_modules/react/index.js
var react = __webpack_require__(7294);
// EXTERNAL MODULE: ./node_modules/@mdx-js/react/dist/esm.js
var esm = __webpack_require__(3905);
;// CONCATENATED MODULE: ../CONTRIBUTING.md
/* @jsxRuntime classic */ /* @jsx mdx */ /* @jsxFrag React.Fragment */const frontMatter={};const contentTitle='Contributing Guide';const toc=[{value:'Table of Contents',id:'table-of-contents',level:2},{value:'Code of Conduct',id:'code-of-conduct',level:2},{value:'Setting Up a Development Environment',id:'setting-up-a-development-environment',level:2},{value:'Prerequisites',id:'prerequisites',level:3},{value:'Getting the Source Code',id:'getting-the-source-code',level:3},{value:'Building the Project',id:'building-the-project',level:3},{value:'Importing the Project to Your IDE',id:'importing-the-project-to-your-ide',level:3},{value:'Running Tests',id:'running-tests',level:3},{value:'Reporting bugs',id:'reporting-bugs',level:2},{value:'Suggesting Enhancements',id:'suggesting-enhancements',level:2},{value:'Improving documentation',id:'improving-documentation',level:2},{value:'Principles',id:'principles',level:2},{value:'Practices',id:'practices',level:2},{value:'Create your first GitHub issue',id:'create-your-first-github-issue',level:2},{value:'Your First Code Contribution',id:'your-first-code-contribution',level:2},{value:'Get in touch!',id:'get-in-touch',level:2}];const layoutProps={toc};const MDXLayout="wrapper";function MDXContent(_ref){let{components,...props}=_ref;return (0,esm/* mdx */.kt)(MDXLayout,(0,esm_extends/* default */.Z)({},layoutProps,props,{components:components,mdxType:"MDXLayout"}),(0,esm/* mdx */.kt)("h1",{"id":"contributing-guide"},`Contributing Guide`),(0,esm/* mdx */.kt)("p",null,`Welcome and thank you for your interest in contributing to our project! This endeavor thrives on collaboration and the
unique inputs from our diverse community. Whether you're fixing bugs, suggesting enhancements, improving our
documentation, or sharing ideas, your contribution is valuable to us.`),(0,esm/* mdx */.kt)("p",null,`Every idea, big or small, holds the potential to make a significant impact, and we're excited to see your inspirational
contributions. You don't have to be an expert in coding to contribute and make a difference. Read on to find how you can
participate and help us continue to expand the boundaries of our project. Welcome aboard!`),(0,esm/* mdx */.kt)("h2",{"id":"table-of-contents"},`Table of Contents`),(0,esm/* mdx */.kt)("ol",null,(0,esm/* mdx */.kt)("li",{parentName:"ol"},(0,esm/* mdx */.kt)("a",{parentName:"li","href":"#code-of-conduct"},`Code of Conduct`)),(0,esm/* mdx */.kt)("li",{parentName:"ol"},(0,esm/* mdx */.kt)("a",{parentName:"li","href":"#setting-up-a-development-environment"},`Setting Up a Development Environment`)),(0,esm/* mdx */.kt)("li",{parentName:"ol"},(0,esm/* mdx */.kt)("a",{parentName:"li","href":"#reporting-bugs"},`Reporting Bugs`)),(0,esm/* mdx */.kt)("li",{parentName:"ol"},(0,esm/* mdx */.kt)("a",{parentName:"li","href":"#suggesting-enhancements"},`Suggesting Enhancements`)),(0,esm/* mdx */.kt)("li",{parentName:"ol"},(0,esm/* mdx */.kt)("a",{parentName:"li","href":"#improving-documentation"},`Improving Documentation`)),(0,esm/* mdx */.kt)("li",{parentName:"ol"},(0,esm/* mdx */.kt)("a",{parentName:"li","href":"#create-your-first-github-issue"},`Create your First GitHub Issue`)),(0,esm/* mdx */.kt)("li",{parentName:"ol"},(0,esm/* mdx */.kt)("a",{parentName:"li","href":"#your-first-code-contribution"},`Your First Code Contribution`))),(0,esm/* mdx */.kt)("h2",{"id":"code-of-conduct"},`Code of Conduct`),(0,esm/* mdx */.kt)("p",null,`Everyone participating in this project is expected to abide by the `,(0,esm/* mdx */.kt)("a",{parentName:"p","href":"CODE_OF_CONDUCT"},`eLLMental Code of Conduct`),`,
which is based on the Contributor Covenant Code of Conduct.`),(0,esm/* mdx */.kt)("p",null,`If you encounter any behavior that violates our code of conduct, please send an email to `,(0,esm/* mdx */.kt)("inlineCode",{parentName:"p"},`info@theagilemonkeys.com`),` or a
direct message to any of the administrators in our official `,(0,esm/* mdx */.kt)("a",{parentName:"p","href":"https://discord.gg/34cBbvjjAx"},`Discord server`),`. All reports
will be examined and investigated promptly and in a manner that respects the privacy and safety of the affected
individual(s).`),(0,esm/* mdx */.kt)("h2",{"id":"setting-up-a-development-environment"},`Setting Up a Development Environment`),(0,esm/* mdx */.kt)("p",null,`Here's how you can prep your development environment for contributing to this Gradle project written in Java.`),(0,esm/* mdx */.kt)("h3",{"id":"prerequisites"},`Prerequisites`),(0,esm/* mdx */.kt)("ol",null,(0,esm/* mdx */.kt)("li",{parentName:"ol"},(0,esm/* mdx */.kt)("p",{parentName:"li"},`Ensure you have Java Development Kit (JDK) installed on your local machine. This is needed to run the Java
compiler. You can download the JDK from
the `,(0,esm/* mdx */.kt)("a",{parentName:"p","href":"https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html"},`official Oracle website`),` or install
it with a package manager of your choice.`)),(0,esm/* mdx */.kt)("li",{parentName:"ol"},(0,esm/* mdx */.kt)("p",{parentName:"li"},`Install Gradle on your machine. The detailed process of installation on different platforms can be found at the `,(0,esm/* mdx */.kt)("a",{parentName:"p","href":"https://gradle.org/install/"},`official installation guide`),`.`))),(0,esm/* mdx */.kt)("h3",{"id":"getting-the-source-code"},`Getting the Source Code`),(0,esm/* mdx */.kt)("ol",null,(0,esm/* mdx */.kt)("li",{parentName:"ol"},(0,esm/* mdx */.kt)("p",{parentName:"li"},`Fork the repository from the main GitHub page (`,(0,esm/* mdx */.kt)("a",{parentName:"p","href":"https://github.com/theam/ellmental"},`theam/ellmental`),`).`)),(0,esm/* mdx */.kt)("li",{parentName:"ol"},(0,esm/* mdx */.kt)("p",{parentName:"li"},`Clone your forked repository to your local machine.`),(0,esm/* mdx */.kt)("pre",{parentName:"li"},(0,esm/* mdx */.kt)("code",{parentName:"pre"},`git clone https://github.com/<Your Username>/eLLMental.git
`))),(0,esm/* mdx */.kt)("li",{parentName:"ol"},(0,esm/* mdx */.kt)("p",{parentName:"li"},`Navigate into the cloned repository:`),(0,esm/* mdx */.kt)("pre",{parentName:"li"},(0,esm/* mdx */.kt)("code",{parentName:"pre"},`cd eLLMental
`)))),(0,esm/* mdx */.kt)("h3",{"id":"building-the-project"},`Building the Project`),(0,esm/* mdx */.kt)("ol",null,(0,esm/* mdx */.kt)("li",{parentName:"ol"},(0,esm/* mdx */.kt)("p",{parentName:"li"},`To build the project, run the following command in your terminal:`),(0,esm/* mdx */.kt)("pre",{parentName:"li"},(0,esm/* mdx */.kt)("code",{parentName:"pre"},`./gradlew build
`)))),(0,esm/* mdx */.kt)("h3",{"id":"importing-the-project-to-your-ide"},`Importing the Project to Your IDE`),(0,esm/* mdx */.kt)("p",null,`If you are using IntelliJ IDEA, importing a Gradle project is as easy as File -> Open, and then open the project's
folder.`),(0,esm/* mdx */.kt)("h3",{"id":"running-tests"},`Running Tests`),(0,esm/* mdx */.kt)("p",null,`To ensure that your setup is working correctly, try running the existing tests in the project repository:`),(0,esm/* mdx */.kt)("pre",null,(0,esm/* mdx */.kt)("code",{parentName:"pre"},`./gradlew test
`)),(0,esm/* mdx */.kt)("p",null,`Tests may take a while to run, but if you see the first few of them passing, your setup is probably correct, so you can
cancel the process or wait for all tests to pass.`),(0,esm/* mdx */.kt)("p",null,`That’s it! You are now all set up and ready to contribute. Remember, if you have any trouble or need further
clarification with setting up the environment, don’t hesitate to reach out. We are more than happy to assist you.`),(0,esm/* mdx */.kt)("h2",{"id":"reporting-bugs"},`Reporting bugs`),(0,esm/* mdx */.kt)("p",null,`Before creating a bug report, please search for similar issues to make sure that they're not already reported. If you
don't find any, go ahead and create an issue including as many details as possible.`),(0,esm/* mdx */.kt)("blockquote",null,(0,esm/* mdx */.kt)("p",{parentName:"blockquote"},`If you find a Closed issue that seems related to the issues that you're experiencing, make sure to reference it in the
body of your new one by writing its number like this => #42 (Github will auto-link it for you).`)),(0,esm/* mdx */.kt)("p",null,`Bugs are tracked as GitHub issues. Explain the problem and include additional details to help maintainers reproduce the
problem:`),(0,esm/* mdx */.kt)("ul",null,(0,esm/* mdx */.kt)("li",{parentName:"ul"},`Use a clear and descriptive title for the issue to identify the problem.`),(0,esm/* mdx */.kt)("li",{parentName:"ul"},`Describe the exact steps which reproduce the problem in as many details as possible.`),(0,esm/* mdx */.kt)("li",{parentName:"ul"},`Provide specific examples to demonstrate the steps. Include links to files or GitHub projects, or copy/pasteable
snippets, which you use in those examples. If you're providing snippets in the issue, use Markdown code blocks.`),(0,esm/* mdx */.kt)("li",{parentName:"ul"},`Describe the behavior you observed after following the steps and point out what exactly is the problem with that
behavior.`),(0,esm/* mdx */.kt)("li",{parentName:"ul"},`Explain which behavior you expected to see instead and why.`),(0,esm/* mdx */.kt)("li",{parentName:"ul"},`If the problem is related to performance or memory, include a CPU profile capture with your report.`)),(0,esm/* mdx */.kt)("blockquote",null,(0,esm/* mdx */.kt)("p",{parentName:"blockquote"},`Remember to label the issue with a "bug" tag`)),(0,esm/* mdx */.kt)("h2",{"id":"suggesting-enhancements"},`Suggesting Enhancements`),(0,esm/* mdx */.kt)("p",null,`Enhancement suggestions are tracked as GitHub issues. Make sure you provide the following information:`),(0,esm/* mdx */.kt)("ul",null,(0,esm/* mdx */.kt)("li",{parentName:"ul"},`Use a clear and descriptive title for the issue to identify the suggestion.`),(0,esm/* mdx */.kt)("li",{parentName:"ul"},`Provide a step-by-step description of the suggested enhancement in as many details as possible.`),(0,esm/* mdx */.kt)("li",{parentName:"ul"},`Provide specific examples to demonstrate the steps. Include copy/pasteable snippets which you use in those examples,
as Markdown code blocks.`),(0,esm/* mdx */.kt)("li",{parentName:"ul"},`Describe the current behavior and explain which behavior you expected to see instead and why.`),(0,esm/* mdx */.kt)("li",{parentName:"ul"},`Explain why this enhancement would be useful to most eLLMental users and isn't something that can or should be
implemented as a community package.`),(0,esm/* mdx */.kt)("li",{parentName:"ul"},`List some other libraries or frameworks where this enhancement exists.`)),(0,esm/* mdx */.kt)("blockquote",null,(0,esm/* mdx */.kt)("p",{parentName:"blockquote"},`Remember to label the issue with an "enhancement" tag`)),(0,esm/* mdx */.kt)("h2",{"id":"improving-documentation"},`Improving documentation`),(0,esm/* mdx */.kt)("p",null,`The `,(0,esm/* mdx */.kt)("a",{parentName:"p","href":"https://docs.ellmental.com"},`eLLMental documentation`),` is treated as a live document that continues improving on a
daily basis. If you find something that is missing or can be improved, please contribute, it will be of great help for
other developers.
To contribute you can use the button "Edit in GitHub" at the top of each chapter.`),(0,esm/* mdx */.kt)("h2",{"id":"principles"},`Principles`),(0,esm/* mdx */.kt)("p",null,`The ultimate goal of a technical document is to `,(0,esm/* mdx */.kt)("strong",{parentName:"p"},`translate the knowledge from the technology creators into the reader's mind`),` so that they learn. The challenging
part here is the one in which they learn. It is challenging because, under the same amount of information, a person can suffer an information overload because
we (humans) don't have the same information-processing capacity. That idea is going to work as our compass, it should drive our efforts so people with less
capacity is still able to follow and understand our documentation.`),(0,esm/* mdx */.kt)("p",null,`To achieve our goal we propose `,(0,esm/* mdx */.kt)("strong",{parentName:"p"},`writing documentation following these principles:`)),(0,esm/* mdx */.kt)("table",null,(0,esm/* mdx */.kt)("thead",{parentName:"table"},(0,esm/* mdx */.kt)("tr",{parentName:"thead"},(0,esm/* mdx */.kt)("th",{parentName:"tr","align":null}),(0,esm/* mdx */.kt)("th",{parentName:"tr","align":null}))),(0,esm/* mdx */.kt)("tbody",{parentName:"table"},(0,esm/* mdx */.kt)("tr",{parentName:"tbody"},(0,esm/* mdx */.kt)("td",{parentName:"tr","align":null},`✔️ `,(0,esm/* mdx */.kt)("strong",{parentName:"td"},`Clean and Clear`)),(0,esm/* mdx */.kt)("td",{parentName:"tr","align":null},`Less is more. Apple is, among many others, a good example of creating clean and clear content, where visual elements are carefully chosen to look beautiful (e.g. `,(0,esm/* mdx */.kt)("a",{parentName:"td","href":"https://developer.apple.com/tutorials/swiftui"},`Apple's swift UI`),`) and making the reader getting the point as soon as possible. `,(0,esm/* mdx */.kt)("br",null),` `,(0,esm/* mdx */.kt)("br",null),` The intention of every section, paragraph, and sentence must be clear, we should avoid writing details of two different things even when they are related.It is better to link pages and keep the focus and the intention clear, Wikipedia is the best example on this.`)),(0,esm/* mdx */.kt)("tr",{parentName:"tbody"},(0,esm/* mdx */.kt)("td",{parentName:"tr","align":null},`👌 `,(0,esm/* mdx */.kt)("strong",{parentName:"td"},`Simple`)),(0,esm/* mdx */.kt)("td",{parentName:"tr","align":null},`Technical writings deal with different backgrounds and expertise from the readers. We should not assume the reader knows everything we are talking about but we should not explain everything in the same paragraph or section. Every section has a goal to stick to the goal and link to internal or external resources to go deeper. `,(0,esm/* mdx */.kt)("br",null),` `,(0,esm/* mdx */.kt)("br",null),` Diagrams are great tools, you know a picture is worth more than a thousand words unless that picture contains too much information. Keep it simple intentionally omitting details.`)),(0,esm/* mdx */.kt)("tr",{parentName:"tbody"},(0,esm/* mdx */.kt)("td",{parentName:"tr","align":null},`💯 `,(0,esm/* mdx */.kt)("strong",{parentName:"td"},`Coherent`)),(0,esm/* mdx */.kt)("td",{parentName:"tr","align":null},`The documentation tells a story. Every section should integrate naturally without making the reader switch between different contexts. Text, diagrams, and code examples should support each other without introducing abrupt changes breaking the reader’s flow. Also, the font, colors, diagrams, code samples, animations, and all the visual elements we include, should support the story we are telling.`)),(0,esm/* mdx */.kt)("tr",{parentName:"tbody"},(0,esm/* mdx */.kt)("td",{parentName:"tr","align":null},`🎯 `,(0,esm/* mdx */.kt)("strong",{parentName:"td"},`Explicit`)),(0,esm/* mdx */.kt)("td",{parentName:"tr","align":null},`Go straight to the point without assuming the readers should know about something. Again, link internal or external resources to clarify. `,(0,esm/* mdx */.kt)("br",null),` `,(0,esm/* mdx */.kt)("br",null),` The index of the whole content must be visible all the time so the reader knows exactly where they are and what is left.`)),(0,esm/* mdx */.kt)("tr",{parentName:"tbody"},(0,esm/* mdx */.kt)("td",{parentName:"tr","align":null},`😍 `,(0,esm/* mdx */.kt)("strong",{parentName:"td"},`Attractive`)),(0,esm/* mdx */.kt)("td",{parentName:"tr","align":null},`Our text must be nice to read, our diagrams delectable to see, and our site… a feast for the eyes!!`)),(0,esm/* mdx */.kt)("tr",{parentName:"tbody"},(0,esm/* mdx */.kt)("td",{parentName:"tr","align":null},`🔗 `,(0,esm/* mdx */.kt)("strong",{parentName:"td"},`Inclusive`)),(0,esm/* mdx */.kt)("td",{parentName:"tr","align":null},`Everybody should understand our writings, especially the topics at the top. We have arranged the documentation structure in a way that anybody can dig deeper by just going down so, sections 1 to 4 must be suitable for all ages. `,(0,esm/* mdx */.kt)("br",null),` `,(0,esm/* mdx */.kt)("br",null),` Use gender-neutral language to avoid the use of he, him, his to refer to undetermined gender. It is better to use their or they as a gender-neutralapproach than s/he or similars.`)),(0,esm/* mdx */.kt)("tr",{parentName:"tbody"},(0,esm/* mdx */.kt)("td",{parentName:"tr","align":null},`✍️ `,(0,esm/* mdx */.kt)("strong",{parentName:"td"},`Cohesive`)),(0,esm/* mdx */.kt)("td",{parentName:"tr","align":null},`Writing short and concise sentences is good, but remember to use proper connectors (“Therefore”, “Besides”, “However”, “thus”, etc) that provide a sense of continuation to the whole paragraph. If not, when people read the paragraphs, their internal voice sounds like a robot with unnatural stops.`)))),(0,esm/* mdx */.kt)("h2",{"id":"practices"},`Practices`),(0,esm/* mdx */.kt)("p",null,`There are many writing styles depending on the type of document. It is common within technical and scientific writing to
use Inductive and/or Deductive styles
for paragraphs. They have different outcomes and one style may suit better in one case or another, that is why it is
important to know them, and decide which
one to use in every moment. Let’s see the difference with 2 recursive examples.`),(0,esm/* mdx */.kt)("p",null,(0,esm/* mdx */.kt)("strong",{parentName:"p"},`Deductive paragraphs ease the reading for advanced users but still allows you to elaborate on ideas and concepts for
newcomers`),`. In deductive paragraphs,
the conclusions or definitions appear at the beginning, and then, details, facts, or supporting phrases complete the
paragraph’s idea. By placing the
conclusion in the first sentence, the reader immediately identifies the main point so they can decide to skip the whole
paragraph or keep reading.
If you take a look at the structure of this paragraph, it is deductive.`),(0,esm/* mdx */.kt)("p",null,`On the other hand, if you want to drive the readers' attention and play with it as if they were in a roller coaster, you
can do so by using a different approach.
In that approach, you first introduce the facts and ideas and then you wrap them with a conclusion. This style is more
narrative and forces the reader to
continue because the main idea is diluted in the whole paragraph. Once all the ideas are placed together, you can
finally conclude the paragraph. `,(0,esm/* mdx */.kt)("strong",{parentName:"p"},`This style is
called Inductive.`)),(0,esm/* mdx */.kt)("p",null,`The first paragraph is deductive and the last one is inductive. In general, it is better to use the deductive style, but
if we stick to one, our writing will start looking weird and maybe boring.
So decide one or another being conscious about your intention.`),(0,esm/* mdx */.kt)("h2",{"id":"create-your-first-github-issue"},`Create your first GitHub issue`),(0,esm/* mdx */.kt)("p",null,(0,esm/* mdx */.kt)("a",{parentName:"p","href":"https://github.com/theam/eLLMental/issues/new"},`Click here`),` to start making contributions to eLLMental.`),(0,esm/* mdx */.kt)("h2",{"id":"your-first-code-contribution"},`Your First Code Contribution`),(0,esm/* mdx */.kt)("p",null,`Unsure where to begin contributing to eLLMental? You can start by looking through issued tagged as `,(0,esm/* mdx */.kt)("inlineCode",{parentName:"p"},`good-first-issue`),`
and `,(0,esm/* mdx */.kt)("inlineCode",{parentName:"p"},`help-wanted`),`:`),(0,esm/* mdx */.kt)("ul",null,(0,esm/* mdx */.kt)("li",{parentName:"ul"},`Beginner issues - issues which should only require a few lines of code, and a test or two.`),(0,esm/* mdx */.kt)("li",{parentName:"ul"},`Help wanted issues - issues which should be a bit more involved than beginner issues.`)),(0,esm/* mdx */.kt)("p",null,`Both issue lists are sorted by the total number of comments. While not perfect, number of comments is a reasonable proxy
for impact a given change will have.`),(0,esm/* mdx */.kt)("p",null,`Once you've chosen an issue to work on, please assign it to yourself. This helps communicate your intention to
contribute and reduces the chance of duplicate work.`),(0,esm/* mdx */.kt)("h2",{"id":"get-in-touch"},`Get in touch!`),(0,esm/* mdx */.kt)("p",null,`If you feel lost, don't hesitate to reach out the core team. You can connect with us via email writing
to `,(0,esm/* mdx */.kt)("a",{parentName:"p","href":"mailto:info@theagilemonkeys.com"},`info@theagilemonkeys.com`),`, or joining our
official `,(0,esm/* mdx */.kt)("a",{parentName:"p","href":"https://discord.gg/34cBbvjjAx"},`Discord server`),`. We will be more than happy to hear about you!`));};MDXContent.isMDXComponent=true;
;// CONCATENATED MODULE: ./docs/04_community/contributing.mdx
/* @jsxRuntime classic */ /* @jsx mdx */ /* @jsxFrag React.Fragment */const contributing_frontMatter={};const contributing_contentTitle='Contributing Guide';const metadata={"unversionedId":"community/contributing","id":"community/contributing","title":"Contributing Guide","description":"","source":"@site/docs/04_community/contributing.mdx","sourceDirName":"04_community","slug":"/community/contributing","permalink":"/community/contributing","draft":false,"tags":[],"version":"current","lastUpdatedBy":"Nick Tchayka","lastUpdatedAt":1693575626,"formattedLastUpdatedAt":"Sep 1, 2023","frontMatter":{},"sidebar":"docs","previous":{"title":"Community","permalink":"/community/"},"next":{"title":"Contributor Covenant Code of Conduct","permalink":"/community/code_of_conduct"}};const assets={};const contributing_toc=[];const contributing_layoutProps={toc: contributing_toc};const contributing_MDXLayout="wrapper";function contributing_MDXContent(_ref){let{components,...props}=_ref;return (0,esm/* mdx */.kt)(contributing_MDXLayout,(0,esm_extends/* default */.Z)({},contributing_layoutProps,props,{components:components,mdxType:"MDXLayout"}),(0,esm/* mdx */.kt)("h1",{"id":"contributing-guide"},`Contributing Guide`),(0,esm/* mdx */.kt)("div",{class:"hiddenh1s"},(0,esm/* mdx */.kt)(MDXContent,{mdxType:"ContributingGuide"})));};contributing_MDXContent.isMDXComponent=true;

/***/ })

}]);