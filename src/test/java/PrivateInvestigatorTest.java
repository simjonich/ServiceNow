import Model.SimilarSentencesGroup;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(DataProviderRunner.class)
public class PrivateInvestigatorTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @DataProvider
    public static Object[][] dataProvider() {
        return new Object[][]{
                {
                    null,
                    Stream.of("").collect(Collectors.toList())
                },
                {
                    null,
                    Stream.of("a b c").collect(Collectors.toList())
                },
                {
                    new ArrayList<>(),
                    Stream.of("01-01-2012 19:45:00").collect(Collectors.toList())
                },
                {
                    new ArrayList<>(),
                    Stream.of("01-01-2012 19:45:00 a b c").collect(Collectors.toList())
                },
                {
                    Stream.of(new SimilarSentencesGroup(Stream.of("01-01-2012 19:45:00 a b c", "02-01-2012 19:45:00 a b d", "03-01-2012 19:45:00 a b e").collect(Collectors.toCollection(LinkedHashSet::new)),
                    Stream.of("c", "d", "e").collect(Collectors.toSet()))).collect(Collectors.toList()),
                    Stream.of("01-01-2012 19:45:00 a b c", "02-01-2012 19:45:00 a b d", "03-01-2012 19:45:00 a b e").collect(Collectors.toList()),
                },
                {
                    Stream.of(
                        new SimilarSentencesGroup(
                            Stream.of("03-01-2012 19:45:00 a g h", "04-01-2012 19:45:00 a g c").collect(Collectors.toCollection(LinkedHashSet::new)),
                            Stream.of("h","c").collect(Collectors.toSet())
                        ),
                        new SimilarSentencesGroup(
                            Stream.of("01-01-2012 19:45:00 a b c", "04-01-2012 19:45:00 a g c").collect(Collectors.toCollection(LinkedHashSet::new)),
                            Stream.of("b", "g").collect(Collectors.toSet()))
                    ).collect(Collectors.toList()),
                    Stream.of("01-01-2012 19:45:00 a b c", "02-01-2012 19:45:00 a d e", "03-01-2012 19:45:00 a g h", "04-01-2012 19:45:00 a g c").collect(Collectors.toList()),
                }

        };
    }

    @Test
    @UseDataProvider("dataProvider")
    public void investigate(List<SimilarSentencesGroup> expected, List<String> input) throws Exception {
        if (expected == null) {
            exception.expect(Exception.class);
        }
        PrivateInvestigator privateInvestigator = new PrivateInvestigator();
        List<SimilarSentencesGroup> actual = privateInvestigator.investigate(input);
        Assert.assertEquals(expected, actual);
    }
}
