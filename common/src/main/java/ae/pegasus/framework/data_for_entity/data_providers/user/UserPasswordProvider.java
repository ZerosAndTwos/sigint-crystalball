package ae.pegasus.framework.data_for_entity.data_providers.user;

import ae.pegasus.framework.data_for_entity.data_providers.EntityDataProvider;
import org.apache.commons.lang3.RandomStringUtils;

import static ae.pegasus.framework.utils.StringUtils.hasRepeatedCharacters;
import static ae.pegasus.framework.utils.StringUtils.hasUpperAndLowerCharacters;

public class UserPasswordProvider implements EntityDataProvider {

  private static final int MAX_REPEATED_CHARS = 3;

  @Override
  public String generate(int length) {
    String password;
    do {
      password = RandomStringUtils.randomAlphanumeric(length);
    } while (hasRepeatedCharacters(password, MAX_REPEATED_CHARS) || !hasUpperAndLowerCharacters(password));
    return password;
  }
}