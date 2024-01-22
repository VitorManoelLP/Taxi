import {AuthValidator} from "../auth-validators";

describe('AuthValidator', () => {

  test('should extract error message by errors', () => {
    const requiredMessage = AuthValidator.extractError({ 'required': true });
    expect(requiredMessage).toEqual('required');

    const invalidMessage = AuthValidator.extractError({ 'invalid': 'Invalid Message' });
    expect(invalidMessage).toEqual('Invalid Message');

    const invalidMessages = AuthValidator.extractError({ 'invalid': 'Invalid Message', 'invalid-message2': 'Invalid Message 2' });
    expect(invalidMessages).toEqual('Invalid Message\nInvalid Message 2');
  });

});
