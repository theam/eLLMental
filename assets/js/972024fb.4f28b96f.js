"use strict";
(self["webpackChunkellmental"] = self["webpackChunkellmental"] || []).push([[178],{

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

/***/ 6222:
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

// ESM COMPAT FLAG
__webpack_require__.r(__webpack_exports__);

// EXPORTS
__webpack_require__.d(__webpack_exports__, {
  assets: () => (/* binding */ assets),
  contentTitle: () => (/* binding */ code_of_conduct_contentTitle),
  "default": () => (/* binding */ code_of_conduct_MDXContent),
  frontMatter: () => (/* binding */ code_of_conduct_frontMatter),
  metadata: () => (/* binding */ metadata),
  toc: () => (/* binding */ code_of_conduct_toc)
});

// EXTERNAL MODULE: ./node_modules/@babel/runtime/helpers/esm/extends.js
var esm_extends = __webpack_require__(7462);
// EXTERNAL MODULE: ./node_modules/react/index.js
var react = __webpack_require__(7294);
// EXTERNAL MODULE: ./node_modules/@mdx-js/react/dist/esm.js
var esm = __webpack_require__(3905);
;// CONCATENATED MODULE: ../CODE_OF_CONDUCT.md
/* @jsxRuntime classic */ /* @jsx mdx */ /* @jsxFrag React.Fragment */const frontMatter={};const contentTitle='Contributor Covenant Code of Conduct';const toc=[{value:'Our Pledge',id:'our-pledge',level:2},{value:'Our Standards',id:'our-standards',level:2},{value:'Enforcement Responsibilities',id:'enforcement-responsibilities',level:2},{value:'Scope',id:'scope',level:2},{value:'Enforcement',id:'enforcement',level:2},{value:'Enforcement Guidelines',id:'enforcement-guidelines',level:2},{value:'1. Correction',id:'1-correction',level:3},{value:'2. Warning',id:'2-warning',level:3},{value:'3. Temporary Ban',id:'3-temporary-ban',level:3},{value:'4. Permanent Ban',id:'4-permanent-ban',level:3},{value:'Attribution',id:'attribution',level:2}];const layoutProps={toc};const MDXLayout="wrapper";function MDXContent(_ref){let{components,...props}=_ref;return (0,esm/* mdx */.kt)(MDXLayout,(0,esm_extends/* default */.Z)({},layoutProps,props,{components:components,mdxType:"MDXLayout"}),(0,esm/* mdx */.kt)("h1",{"id":"contributor-covenant-code-of-conduct"},`Contributor Covenant Code of Conduct`),(0,esm/* mdx */.kt)("h2",{"id":"our-pledge"},`Our Pledge`),(0,esm/* mdx */.kt)("p",null,`We as members, contributors, and leaders pledge to make participation in our
community a harassment-free experience for everyone, regardless of age, body
size, visible or invisible disability, ethnicity, sex characteristics, gender
identity and expression, level of experience, education, socio-economic status,
nationality, personal appearance, race, religion, or sexual identity
and orientation.`),(0,esm/* mdx */.kt)("p",null,`We pledge to act and interact in ways that contribute to an open, welcoming,
diverse, inclusive, and healthy community.`),(0,esm/* mdx */.kt)("h2",{"id":"our-standards"},`Our Standards`),(0,esm/* mdx */.kt)("p",null,`Examples of behavior that contributes to a positive environment for our
community include:`),(0,esm/* mdx */.kt)("ul",null,(0,esm/* mdx */.kt)("li",{parentName:"ul"},`Demonstrating empathy and kindness toward other people`),(0,esm/* mdx */.kt)("li",{parentName:"ul"},`Being respectful of differing opinions, viewpoints, and experiences`),(0,esm/* mdx */.kt)("li",{parentName:"ul"},`Giving and gracefully accepting constructive feedback`),(0,esm/* mdx */.kt)("li",{parentName:"ul"},`Accepting responsibility and apologizing to those affected by our mistakes,
and learning from the experience`),(0,esm/* mdx */.kt)("li",{parentName:"ul"},`Focusing on what is best not just for us as individuals, but for the
overall community`)),(0,esm/* mdx */.kt)("p",null,`Examples of unacceptable behavior include:`),(0,esm/* mdx */.kt)("ul",null,(0,esm/* mdx */.kt)("li",{parentName:"ul"},`The use of sexualized language or imagery, and sexual attention or
advances of any kind`),(0,esm/* mdx */.kt)("li",{parentName:"ul"},`Trolling, insulting or derogatory comments, and personal or political attacks`),(0,esm/* mdx */.kt)("li",{parentName:"ul"},`Public or private harassment`),(0,esm/* mdx */.kt)("li",{parentName:"ul"},`Publishing others' private information, such as a physical or email
address, without their explicit permission`),(0,esm/* mdx */.kt)("li",{parentName:"ul"},`Other conduct which could reasonably be considered inappropriate in a
professional setting`)),(0,esm/* mdx */.kt)("h2",{"id":"enforcement-responsibilities"},`Enforcement Responsibilities`),(0,esm/* mdx */.kt)("p",null,`Community leaders are responsible for clarifying and enforcing our standards of
acceptable behavior and will take appropriate and fair corrective action in
response to any behavior that they deem inappropriate, threatening, offensive,
or harmful.`),(0,esm/* mdx */.kt)("p",null,`Community leaders have the right and responsibility to remove, edit, or reject
comments, commits, code, wiki edits, issues, and other contributions that are
not aligned to this Code of Conduct, and will communicate reasons for moderation
decisions when appropriate.`),(0,esm/* mdx */.kt)("h2",{"id":"scope"},`Scope`),(0,esm/* mdx */.kt)("p",null,`This Code of Conduct applies within all community spaces, and also applies when
an individual is officially representing the community in public spaces.
Examples of representing our community include using an official e-mail address,
posting via an official social media account, or acting as an appointed
representative at an online or offline event.`),(0,esm/* mdx */.kt)("h2",{"id":"enforcement"},`Enforcement`),(0,esm/* mdx */.kt)("p",null,`Instances of abusive, harassing, or otherwise unacceptable behavior may be
reported to the community leaders responsible for enforcement at
`,(0,esm/* mdx */.kt)("a",{parentName:"p","href":"mailto:info@theagilemonkeys.com."},`info@theagilemonkeys.com.`),`
All complaints will be reviewed and investigated promptly and fairly.`),(0,esm/* mdx */.kt)("p",null,`All community leaders are obligated to respect the privacy and security of the
reporter of any incident.`),(0,esm/* mdx */.kt)("h2",{"id":"enforcement-guidelines"},`Enforcement Guidelines`),(0,esm/* mdx */.kt)("p",null,`Community leaders will follow these Community Impact Guidelines in determining
the consequences for any action they deem in violation of this Code of Conduct:`),(0,esm/* mdx */.kt)("h3",{"id":"1-correction"},`1. Correction`),(0,esm/* mdx */.kt)("p",null,(0,esm/* mdx */.kt)("strong",{parentName:"p"},`Community Impact`),`: Use of inappropriate language or other behavior deemed
unprofessional or unwelcome in the community.`),(0,esm/* mdx */.kt)("p",null,(0,esm/* mdx */.kt)("strong",{parentName:"p"},`Consequence`),`: A private, written warning from community leaders, providing
clarity around the nature of the violation and an explanation of why the
behavior was inappropriate. A public apology may be requested.`),(0,esm/* mdx */.kt)("h3",{"id":"2-warning"},`2. Warning`),(0,esm/* mdx */.kt)("p",null,(0,esm/* mdx */.kt)("strong",{parentName:"p"},`Community Impact`),`: A violation through a single incident or series
of actions.`),(0,esm/* mdx */.kt)("p",null,(0,esm/* mdx */.kt)("strong",{parentName:"p"},`Consequence`),`: A warning with consequences for continued behavior. No
interaction with the people involved, including unsolicited interaction with
those enforcing the Code of Conduct, for a specified period of time. This
includes avoiding interactions in community spaces as well as external channels
like social media. Violating these terms may lead to a temporary or
permanent ban.`),(0,esm/* mdx */.kt)("h3",{"id":"3-temporary-ban"},`3. Temporary Ban`),(0,esm/* mdx */.kt)("p",null,(0,esm/* mdx */.kt)("strong",{parentName:"p"},`Community Impact`),`: A serious violation of community standards, including
sustained inappropriate behavior.`),(0,esm/* mdx */.kt)("p",null,(0,esm/* mdx */.kt)("strong",{parentName:"p"},`Consequence`),`: A temporary ban from any sort of interaction or public
communication with the community for a specified period of time. No public or
private interaction with the people involved, including unsolicited interaction
with those enforcing the Code of Conduct, is allowed during this period.
Violating these terms may lead to a permanent ban.`),(0,esm/* mdx */.kt)("h3",{"id":"4-permanent-ban"},`4. Permanent Ban`),(0,esm/* mdx */.kt)("p",null,(0,esm/* mdx */.kt)("strong",{parentName:"p"},`Community Impact`),`: Demonstrating a pattern of violation of community
standards, including sustained inappropriate behavior,  harassment of an
individual, or aggression toward or disparagement of classes of individuals.`),(0,esm/* mdx */.kt)("p",null,(0,esm/* mdx */.kt)("strong",{parentName:"p"},`Consequence`),`: A permanent ban from any sort of public interaction within
the community.`),(0,esm/* mdx */.kt)("h2",{"id":"attribution"},`Attribution`),(0,esm/* mdx */.kt)("p",null,`This Code of Conduct is adapted from the `,(0,esm/* mdx */.kt)("a",{parentName:"p","href":"https://www.contributor-covenant.org"},`Contributor Covenant`),`,
version 2.0, available at
`,(0,esm/* mdx */.kt)("a",{parentName:"p","href":"https://www.contributor-covenant.org/version/2/0/code_of_conduct.html"},`https://www.contributor-covenant.org/version/2/0/code_of_conduct.html`),`.`),(0,esm/* mdx */.kt)("p",null,`Community Impact Guidelines were inspired by `,(0,esm/* mdx */.kt)("a",{parentName:"p","href":"https://github.com/mozilla/diversity"},`Mozilla's code of conduct
enforcement ladder`),`.`),(0,esm/* mdx */.kt)("p",null,`For answers to common questions about this code of conduct, see the FAQ at
`,(0,esm/* mdx */.kt)("a",{parentName:"p","href":"https://www.contributor-covenant.org/faq"},`https://www.contributor-covenant.org/faq`),`. Translations are available at
`,(0,esm/* mdx */.kt)("a",{parentName:"p","href":"https://www.contributor-covenant.org/translations"},`https://www.contributor-covenant.org/translations`),`.`));};MDXContent.isMDXComponent=true;
;// CONCATENATED MODULE: ./docs/04_community/code_of_conduct.mdx
/* @jsxRuntime classic */ /* @jsx mdx */ /* @jsxFrag React.Fragment */const code_of_conduct_frontMatter={};const code_of_conduct_contentTitle='Contributor Covenant Code of Conduct';const metadata={"unversionedId":"community/code_of_conduct","id":"community/code_of_conduct","title":"Contributor Covenant Code of Conduct","description":"","source":"@site/docs/04_community/code_of_conduct.mdx","sourceDirName":"04_community","slug":"/community/code_of_conduct","permalink":"/community/code_of_conduct","draft":false,"tags":[],"version":"current","lastUpdatedBy":"Nick Tchayka","lastUpdatedAt":1693574698,"formattedLastUpdatedAt":"Sep 1, 2023","frontMatter":{},"sidebar":"docs","previous":{"title":"Contributing Guide","permalink":"/community/contributing"}};const assets={};const code_of_conduct_toc=[];const code_of_conduct_layoutProps={toc: code_of_conduct_toc};const code_of_conduct_MDXLayout="wrapper";function code_of_conduct_MDXContent(_ref){let{components,...props}=_ref;return (0,esm/* mdx */.kt)(code_of_conduct_MDXLayout,(0,esm_extends/* default */.Z)({},code_of_conduct_layoutProps,props,{components:components,mdxType:"MDXLayout"}),(0,esm/* mdx */.kt)("h1",{"id":"contributor-covenant-code-of-conduct"},`Contributor Covenant Code of Conduct`),(0,esm/* mdx */.kt)("div",{class:"hiddenh1s"},(0,esm/* mdx */.kt)(MDXContent,{mdxType:"CodeOfConduct"})));};code_of_conduct_MDXContent.isMDXComponent=true;

/***/ })

}]);