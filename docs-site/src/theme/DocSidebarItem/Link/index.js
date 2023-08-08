import DocSidebarItemLink from '@theme-original/DocSidebarItem/Link';
import React from 'react';
import clsx from 'clsx';
import {ThemeClassNames} from '@docusaurus/theme-common';
import {isActiveSidebarItem} from '@docusaurus/theme-common/internal';
import Link from '@docusaurus/Link';
import isInternalUrl from '@docusaurus/isInternalUrl';
import IconExternalLink from '@theme/Icon/ExternalLink';


export default function LinkWrapper(props) {
  // useEffect(() => {
  //   if (itemProps.className?.includes("codetag-sidebar")) {
  //     console.table(props.item)
  //   }
  // }, [])
  const itemProps = props.item
  const tag = itemProps.customProps?.tag
  const name = itemProps.customProps?.name
  if (!tag || !name) {
    return (
      <>
        <DocSidebarItemLink {...props} />
      </>
    );
  }

  return (
    <DocSidebarCodeItemLink {...props} />
  )

}

function DocSidebarCodeItemLink({
                                  item,
                                  onItemClick,
                                  activePath,
                                  level,
                                  index,
                                  ...props
                                }) {
  const {href, label, className, autoAddBaseUrl, customProps} = item;
  const tag = customProps?.tag
  const name = customProps?.name
  const isActive = isActiveSidebarItem(item, activePath);
  const isInternalLink = isInternalUrl(href);
  return (
    <li
      className={clsx(
        ThemeClassNames.docs.docSidebarItemLink,
        ThemeClassNames.docs.docSidebarItemLinkLevel(level),
        'menu__list-item',
        className,
      )}
      key={label}>
      <Link
        className={clsx(
          'menu__link',
          !isInternalLink && styles.menuExternalLink,
          {
            'menu__link--active': isActive,
          },
        )}
        autoAddBaseUrl={autoAddBaseUrl}
        aria-current={isActive ? 'page' : undefined}
        to={href}
        {...(isInternalLink && {
          onClick: onItemClick ? () => onItemClick(item) : undefined,
        })}
        {...props}>
        <span style={{
          opacity: 0.65,
          paddingRight: '5px',
          fontFamily: 'monospace',
        }}>{tag}</span>
        <span style={{
          fontFamily: 'monospace',
        }}>{name}</span>
        {!isInternalLink && <IconExternalLink/>}
      </Link>
    </li>
  );
}