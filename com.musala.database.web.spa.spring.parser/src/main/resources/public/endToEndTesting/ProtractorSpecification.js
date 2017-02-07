
var urlLocation='http://localhost:8080/';
var packageName='';
var viewName='index.html';

var origFn = browser.driver.controlFlow().execute;
browser.driver.controlFlow().execute = function() {
	var args = arguments;
	// queue 50ms wait
	origFn.call(browser.driver.controlFlow(), function() {
		return protractor.promise.delayed(50);
	});
	return origFn.apply(browser.driver.controlFlow(), args);
};

describe('angularjs homepage', function() {
	
  it('should redirect to this', function() {
	  
	  browser.get(urlLocation+packageName+viewName);

	  var serverName=  element(by.model('serverName'));
	  serverName.clear();
	  serverName.sendKeys('smth');
	  
	  var databaseName=  element(by.model('databaseName'));
	  databaseName.clear();
	  databaseName.sendKeys('smth');
	  
	  var userName=  element(by.model('userName'));
	  userName.clear();
	  userName.sendKeys('smth');
	  
	  var pass=  element(by.model('password'));
	  pass.clear();
	  pass.sendKeys('smth');
		
	  element(by.className('btn btn-primary')).click();
	  var falseAnswer=$('#init-failure-box p').getText();
	  expect(falseAnswer).toEqual('false operation');
	  browser.sleep(1000);
  });
  it('should pass to the next template',function(){
		var serverName=  element(by.model('serverName'));
		serverName.clear();
		serverName.sendKeys('localhost');
	  
		var databaseName=  element(by.model('databaseName'));
		databaseName.clear();
		databaseName.sendKeys('schools');
	  
		var userName=  element(by.model('userName'));
		userName.clear();
		userName.sendKeys('root');
	  
		var pass=  element(by.model('password'));
		pass.clear();
		pass.sendKeys('');
		
		element(by.className('btn btn-primary')).click();
		var successAnswer=$('#init-success-box p').getText();
		expect(successAnswer).toEqual('true operation');
		browser.sleep(1000);
   });
   it('should fail the query',function(){
		element(by.model('queriesType')).$('[value="getAllRecords"]').click();
		element(by.model('tableName')).$('[value="schools"]').click();
		element(by.model('properties')).sendKeys('smthh');
		
		element(by.className('btn btn-primary')).click();
		var falseAnswer=$('#init-failure-box p').getText();
		expect(falseAnswer).toEqual('Invalid query');
		browser.sleep(2000);
   });
   it('should execute the simple query',function(){
		element(by.className('btn btn-primary')).click();
		var successAnswer=$('#init-success-box p').getText();
		expect(successAnswer).toEqual('true operation');
		browser.sleep(1000);
		
		element(by.model('queriesType')).$('[value="getAllRecords"]').click();
		element(by.model('tableName')).$('[value="schools"]').click();
		element(by.model('properties')).sendKeys('name');
		
		element(by.className('btn btn-primary')).click();
		var falseAnswer=$('#init-failure-box p').getText();
		expect(falseAnswer).toEqual('Ivan Vazov ,Hristo Botev');
		browser.sleep(2000);
    });
	it('should trigger the 3rd template and fail',function(){
		element(by.className('btn btn-primary')).click();
		var successAnswer=$('#init-success-box p').getText();
		expect(successAnswer).toEqual('true operation');
		browser.sleep(1000);
		
		element(by.model('queriesType')).$('[value="getRecordById"]').click();
		element(by.model('tableName')).$('[value="schools"]').click();
		element(by.model('properties')).sendKeys('name');
		element(by.className('btn btn-primary')).click();
		var successAnswer=$('#init-success-box p').getText();
		expect(successAnswer).toEqual('Succesfull');
		browser.sleep(2000);
		
		element(by.model('id')).sendKeys('123');
		element(by.className('btn btn-primary')).click();
		var successAnswer=$('#init-failure-box p').getText();
		expect(successAnswer).toEqual('Invalid query');
		browser.sleep(2000);
	});
	it('should trigger the 3rd template and fail',function(){
		element(by.className('btn btn-primary')).click();
		var successAnswer=$('#init-success-box p').getText();
		expect(successAnswer).toEqual('true operation');
		browser.sleep(1000);
		
		element(by.model('queriesType')).$('[value="getRecordById"]').click();
		element(by.model('tableName')).$('[value="schools"]').click();
		element(by.model('properties')).sendKeys('name');
		element(by.className('btn btn-primary')).click();
		var successAnswer=$('#init-success-box p').getText();
		expect(successAnswer).toEqual('Succesfull');
		browser.sleep(2000);
		
		element(by.model('id')).sendKeys('2');
		element(by.className('btn btn-primary')).click();
		var successAnswer=$('#init-success-box p').getText();
		expect(successAnswer).toEqual('Hristo Botev');
		browser.sleep(2000);
    });
});