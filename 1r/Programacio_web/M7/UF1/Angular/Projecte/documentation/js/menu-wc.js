'use strict';

customElements.define('compodoc-menu', class extends HTMLElement {
    constructor() {
        super();
        this.isNormalMode = this.getAttribute('mode') === 'normal';
    }

    connectedCallback() {
        this.render(this.isNormalMode);
    }

    render(isNormalMode) {
        let tp = lithtml.html(`
        <nav>
            <ul class="list">
                <li class="title">
                    <a href="index.html" data-type="index-link">projecte documentation</a>
                </li>

                <li class="divider"></li>
                ${ isNormalMode ? `<div id="book-search-input" role="search"><input type="text" placeholder="Type to search"></div>` : '' }
                <li class="chapter">
                    <a data-type="chapter-link" href="index.html"><span class="icon ion-ios-home"></span>Getting started</a>
                    <ul class="links">
                        <li class="link">
                            <a href="overview.html" data-type="chapter-link">
                                <span class="icon ion-ios-keypad"></span>Overview
                            </a>
                        </li>
                        <li class="link">
                            <a href="index.html" data-type="chapter-link">
                                <span class="icon ion-ios-paper"></span>README
                            </a>
                        </li>
                                <li class="link">
                                    <a href="dependencies.html" data-type="chapter-link">
                                        <span class="icon ion-ios-list"></span>Dependencies
                                    </a>
                                </li>
                                <li class="link">
                                    <a href="properties.html" data-type="chapter-link">
                                        <span class="icon ion-ios-apps"></span>Properties
                                    </a>
                                </li>
                    </ul>
                </li>
                    <li class="chapter modules">
                        <a data-type="chapter-link" href="modules.html">
                            <div class="menu-toggler linked" data-bs-toggle="collapse" ${ isNormalMode ?
                                'data-bs-target="#modules-links"' : 'data-bs-target="#xs-modules-links"' }>
                                <span class="icon ion-ios-archive"></span>
                                <span class="link-name">Modules</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                        </a>
                        <ul class="links collapse " ${ isNormalMode ? 'id="modules-links"' : 'id="xs-modules-links"' }>
                            <li class="link">
                                <a href="modules/AppModule.html" data-type="entity-link" >AppModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ?
                                            'data-bs-target="#components-links-module-AppModule-d7124ab9a70ced046d64afe5f1bf9ba2428abba5612e3b16ae9b98dc1189aab761e055727b0c44f495e34ffcb3336b78dbc835c138aa7009b18c8886e0edfc17"' : 'data-bs-target="#xs-components-links-module-AppModule-d7124ab9a70ced046d64afe5f1bf9ba2428abba5612e3b16ae9b98dc1189aab761e055727b0c44f495e34ffcb3336b78dbc835c138aa7009b18c8886e0edfc17"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-AppModule-d7124ab9a70ced046d64afe5f1bf9ba2428abba5612e3b16ae9b98dc1189aab761e055727b0c44f495e34ffcb3336b78dbc835c138aa7009b18c8886e0edfc17"' :
                                            'id="xs-components-links-module-AppModule-d7124ab9a70ced046d64afe5f1bf9ba2428abba5612e3b16ae9b98dc1189aab761e055727b0c44f495e34ffcb3336b78dbc835c138aa7009b18c8886e0edfc17"' }>
                                            <li class="link">
                                                <a href="components/AppComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AppComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                                <li class="chapter inner">
                                    <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ?
                                        'data-bs-target="#directives-links-module-AppModule-d7124ab9a70ced046d64afe5f1bf9ba2428abba5612e3b16ae9b98dc1189aab761e055727b0c44f495e34ffcb3336b78dbc835c138aa7009b18c8886e0edfc17"' : 'data-bs-target="#xs-directives-links-module-AppModule-d7124ab9a70ced046d64afe5f1bf9ba2428abba5612e3b16ae9b98dc1189aab761e055727b0c44f495e34ffcb3336b78dbc835c138aa7009b18c8886e0edfc17"' }>
                                        <span class="icon ion-md-code-working"></span>
                                        <span>Directives</span>
                                        <span class="icon ion-ios-arrow-down"></span>
                                    </div>
                                    <ul class="links collapse" ${ isNormalMode ? 'id="directives-links-module-AppModule-d7124ab9a70ced046d64afe5f1bf9ba2428abba5612e3b16ae9b98dc1189aab761e055727b0c44f495e34ffcb3336b78dbc835c138aa7009b18c8886e0edfc17"' :
                                        'id="xs-directives-links-module-AppModule-d7124ab9a70ced046d64afe5f1bf9ba2428abba5612e3b16ae9b98dc1189aab761e055727b0c44f495e34ffcb3336b78dbc835c138aa7009b18c8886e0edfc17"' }>
                                        <li class="link">
                                            <a href="directives/DirectivesDirective.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >DirectivesDirective</a>
                                        </li>
                                    </ul>
                                </li>
                            </li>
                            <li class="link">
                                <a href="modules/AppRoutingModule.html" data-type="entity-link" >AppRoutingModule</a>
                            </li>
                            <li class="link">
                                <a href="modules/ProjecteModule.html" data-type="entity-link" >ProjecteModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ?
                                            'data-bs-target="#components-links-module-ProjecteModule-fab586520000d6429d9f83abdd442e124f243744e3f020844a4e4b1f7ed4a10bbbe337688e264e275023a39f5bc36b399d727830286dcc83c6a821f75c8189dd"' : 'data-bs-target="#xs-components-links-module-ProjecteModule-fab586520000d6429d9f83abdd442e124f243744e3f020844a4e4b1f7ed4a10bbbe337688e264e275023a39f5bc36b399d727830286dcc83c6a821f75c8189dd"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-ProjecteModule-fab586520000d6429d9f83abdd442e124f243744e3f020844a4e4b1f7ed4a10bbbe337688e264e275023a39f5bc36b399d727830286dcc83c6a821f75c8189dd"' :
                                            'id="xs-components-links-module-ProjecteModule-fab586520000d6429d9f83abdd442e124f243744e3f020844a4e4b1f7ed4a10bbbe337688e264e275023a39f5bc36b399d727830286dcc83c6a821f75c8189dd"' }>
                                            <li class="link">
                                                <a href="components/AjudaComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AjudaComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ClassificacioComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ClassificacioComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/JocComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >JocComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/LoginComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >LoginComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/PaginaPrincipalComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >PaginaPrincipalComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/RegistreComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >RegistreComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                </ul>
                </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#classes-links"' :
                            'data-bs-target="#xs-classes-links"' }>
                            <span class="icon ion-ios-paper"></span>
                            <span>Classes</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="classes-links"' : 'id="xs-classes-links"' }>
                            <li class="link">
                                <a href="classes/Users.html" data-type="entity-link" >Users</a>
                            </li>
                        </ul>
                    </li>
                        <li class="chapter">
                            <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#injectables-links"' :
                                'data-bs-target="#xs-injectables-links"' }>
                                <span class="icon ion-md-arrow-round-down"></span>
                                <span>Injectables</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                            <ul class="links collapse " ${ isNormalMode ? 'id="injectables-links"' : 'id="xs-injectables-links"' }>
                                <li class="link">
                                    <a href="injectables/ConnectDBService.html" data-type="entity-link" >ConnectDBService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/LoginServiceService.html" data-type="entity-link" >LoginServiceService</a>
                                </li>
                            </ul>
                        </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#miscellaneous-links"'
                            : 'data-bs-target="#xs-miscellaneous-links"' }>
                            <span class="icon ion-ios-cube"></span>
                            <span>Miscellaneous</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="miscellaneous-links"' : 'id="xs-miscellaneous-links"' }>
                            <li class="link">
                                <a href="miscellaneous/functions.html" data-type="entity-link">Functions</a>
                            </li>
                            <li class="link">
                                <a href="miscellaneous/variables.html" data-type="entity-link">Variables</a>
                            </li>
                        </ul>
                    </li>
                        <li class="chapter">
                            <a data-type="chapter-link" href="routes.html"><span class="icon ion-ios-git-branch"></span>Routes</a>
                        </li>
                    <li class="chapter">
                        <a data-type="chapter-link" href="coverage.html"><span class="icon ion-ios-stats"></span>Documentation coverage</a>
                    </li>
                    <li class="divider"></li>
                    <li class="copyright">
                        Documentation generated using <a href="https://compodoc.app/" target="_blank" rel="noopener noreferrer">
                            <img data-src="images/compodoc-vectorise.png" class="img-responsive" data-type="compodoc-logo">
                        </a>
                    </li>
            </ul>
        </nav>
        `);
        this.innerHTML = tp.strings;
    }
});