package com.acme.edu;

import com.acme.edu.command.IntCommand;
import com.acme.edu.command.StringCommand;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;

public class StringCommandTest {
    private StringCommand stringCommand;

    @Before
    public void setUp() {
        stringCommand = new StringCommand("test string");
    }

    @Test
    public void shouldReturnTrueWhenNewCommandTypeIsTheSame() {
        assertTrue(stringCommand.isSameType(new StringCommand("test string")));
    }

    @Test
    public void shouldReturnFalseWhenNewCommandIsDifferentString() {
        assertFalse(stringCommand.isSameType(new StringCommand("different string")));
    }

    @Test
    public void shouldReturnFalseWhenNewCommandTypeIsInt() {
        assertFalse(stringCommand.isSameType(new IntCommand(anyInt())));
    }

    @Test
    public void shouldAccumulateDuplicatedStrings() {
        StringCommand duplicatedStringCommand = new StringCommand("test string");
        assertThat(stringCommand.accumulate(duplicatedStringCommand)).usingRecursiveComparison()
                .isEqualTo(new StringCommand("test string", 1));
    }

    @Test
    public void shouldReturnDecoratedCommand(){
        assertEquals("string: test string", stringCommand.toString());
    }

    @Test
    public void shouldReturnDecoratedCommandWithDuplicates(){
        StringCommand duplicatedStringCommand = new StringCommand("test string");

        stringCommand.accumulate(duplicatedStringCommand);

        assertEquals("string: test string (x2)", stringCommand.toString());
    }
}
