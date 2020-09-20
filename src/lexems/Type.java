package lexems;

public class Type implements ILexem{
   public ILexem type = null;

   public Type(PrimitiveType type) {
       this.type = type;
   }

   public Type(ArrayType arrayType) {
       this.type = arrayType;
   }

   public Type(RecordType recordType) {
       this.type = recordType;
   }

   public Type(Identifier identifier) {
       this.type = identifier;
   }

    public Type() {

    }
}
