// An example configuration file.
exports.config = {
		
  directConnect: true,

  // Capabilities to be passed to the webdriver instance.
  multiCapabilities: [
	  {browserName: 'chrome'},
	  {browserName: 'firefox'}
  ],
  maxSessions: 1,
  // Framework to use. Jasmine is recommended.
  framework: 'jasmine',

  // Spec patterns are relative to the current working directory when
  // protractor is called.
  specs: ['ProtractorSpecification.js'],
};
