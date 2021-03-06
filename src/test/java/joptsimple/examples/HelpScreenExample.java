package joptsimple.examples;

import static java.io.File.*;
import static java.util.Arrays.*;
import static joptsimple.util.DateConverter.*;

import java.io.File;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class HelpScreenExample {
    public static void main( String[] args ) throws Exception {
        OptionParser parser = new OptionParser() {
            {
                accepts( "c" ).withRequiredArg().ofType( Integer.class )
                    .describedAs( "count" ).defaultsTo( 1 );
                accepts( "q" ).withOptionalArg().ofType( Double.class )
                    .describedAs( "quantity" );
                accepts( "d", "some date" ).withRequiredArg()
                    .withValuesConvertedBy( datePattern( "MM/dd/yy" ) );
                acceptsAll( asList( "v", "talkative", "chatty" ), "be more verbose" );
                accepts( "output-file" ).withOptionalArg().ofType( File.class )
                     .describedAs( "file" );
                acceptsAll( asList( "h", "?" ), "show help" );
                acceptsAll( asList( "cp", "classpath" ) ).withRequiredArg()
                    .describedAs( "path1" + pathSeparatorChar + "path2:..." )
                    .ofType( File.class )
                    .withValuesSeparatedBy( pathSeparatorChar );
            }
        };

        OptionSet options = parser.parse( args );

        if ( options.has( "?" ) )
            parser.printHelpOn( System.out );
    }
}
