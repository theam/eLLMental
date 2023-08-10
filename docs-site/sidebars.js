/**
 * Creating a sidebar enables you to:
 - create an ordered group of docs
 - render a sidebar for each doc of that group
 - provide next/previous navigation

 The sidebars can be generated from the filesystem, or explicitly defined here.

 Create as many sidebars as you want.
 */

// @ts-check

/** @type {import('@docusaurus/plugin-content-docs').SidebarsConfig} */
const sidebars = {
  // By default, Docusaurus generates a sidebar from the docs folder structure
  docs: [
    'introduction',
    'getting_started',
    {
      type: 'category',
      label: 'Components',

      items: [
        'components/core_abstractions',
        'components/embeddings_space'
      ]
    },
    {
      type: 'category',
      label: 'Community',
      link: {
        type: 'doc',
        id: 'community/index'
      },
      items: [
        'community/contributing',
        {
          type: 'doc',
          id: 'community/code_of_conduct',
          label: 'Code of Conduct'
        }
      ]
    }
  ],

  // But you can create a sidebar manually
  /*
  tutorialSidebar: [
    'intro',
    'hello',
    {
      type: 'category',
      label: 'Tutorial',
      items: ['tutorial-basics/create-a-document'],
    },
  ],
   */
};

module.exports = sidebars;
