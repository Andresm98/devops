package com.anax.devops;

import com.anax.devops.application.usecase.DevOpsProcessor;
import com.anax.devops.domain.model.DevOpsMessage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DevOpsProcessorTest {
    @Test
    void shouldFormatMessageCorrectly() {
        // Arrange
        DevOpsProcessor processor = new DevOpsProcessor();
        DevOpsMessage input = new DevOpsMessage("This is a test", "Juan Perez", "Rita Asturia", 45);

        // Act
        String result = processor.processMessage(input);

        // Assert
        assertEquals("Hello Juan Perez your message will be send", result);
    }


}
