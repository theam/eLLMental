// @ts-check
// Note: type annotations allow type checking and IDEs autocompletion

const lightCodeTheme = require('prism-react-renderer/themes/github');
const darkCodeTheme = require('prism-react-renderer/themes/dracula');

/** @type {import('@docusaurus/types').Config} */
const config = {
  title: 'eLLMental',
  favicon: 'img/favicon.png',

  // Set the production url of your site here
  url: 'https://docs.ellmental.com',
  // Set the /<baseUrl>/ pathname under which your site is served
  // For GitHub pages deployment, it is often '/<projectName>/'
  baseUrl: '/',

  // GitHub pages deployment config.
  // If you aren't using GitHub pages, you don't need these.
  organizationName: 'theam', // Usually your GitHub org/user name.
  projectName: 'ellmental', // Usually your repo name.

  onBrokenLinks: 'throw',
  onBrokenMarkdownLinks: 'warn',

  // Even if you don't use internalization, you can use this field to set useful
  // metadata like html lang. For example, if your site is Chinese, you may want
  // to replace "en" with "zh-Hans".
  i18n: {
    defaultLocale: 'en',
    locales: ['en'],
  },

  presets: [
    [
      'classic',
      /** @type {import('@docusaurus/preset-classic').Options} */
      ({
        docs: {
          routeBasePath: '/',
          sidebarPath: require.resolve('./sidebars.js'),
          showLastUpdateTime: true,
          showLastUpdateAuthor: true,
        },
        blog: false,
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
      }),
    ],
  ],
  plugins: [require.resolve('docusaurus-plugin-fathom')],
  themeConfig:
  /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
      ({
        // Replace with your project's social card
        image: 'img/logoellmental.png',
        stylesheets: [
          "https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap",
        ],
        fathomAnalytics: {
          siteId: 'HUKFMRPY',
        },
        navbar: {
          logo: {
            alt: 'eLLMental Logo',
            src: '/img/eLLMental-logo-black.png',
            srcDark: '/img/eLLMental-logo-white.png'
          },
          items: [
            {
              href: 'https://github.com/theam/eLLMental',
              label: 'GitHub',
              position: 'right',
            },
            {
              href: 'https://discord.gg/34cBbvjjAx',
              label: 'Discord',
              position: 'right',
            },
          ],
        },
        prism: {
          theme: lightCodeTheme,
          darkTheme: darkCodeTheme,
          additionalLanguages: ['java'],
        },
      }),
};

module.exports = config
