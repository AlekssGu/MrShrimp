package server.messages;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ResourceBundleSupplier.class)
public class ResourceBundleSupplierTest {

	@Mock
	private ResourceBundle resourceBundle;

	@InjectMocks
	private ResourceBundleSupplier resourceBundleSupplier;

	@Test
	public void getBundle() {
		mockStatic(ResourceBundle.class);
		PowerMockito.when(ResourceBundle.getBundle(any(), any(Locale.class))).thenReturn(resourceBundle);
		ResourceBundle actualResourceBundle = resourceBundleSupplier.getBundle("randomBundle", Locale.US);

		assertThat(actualResourceBundle).isEqualTo(resourceBundle);
	}
}