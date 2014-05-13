package word_finder;

class TileBlock
{
	public static final int	NORMAL			= 0;
	public static final int	START_STAR		= 1;
	public static final int	DOUBLE_LETTER	= 2;
	public static final int	TRIPLE_LETTER	= 3;
	public static final int	DOUBLE_WORD		= 4;
	public static final int	TRIPLE_WORD		= 5;
	
	
	public enum Block//@formatter: off
	{
		
		BLANK('?', 2, 0), A('A', 9, 1), B('B', 2, 3), C('C', 2, 3), D('D', 2, 4), E('E', 12, 1), F('F', 2, 4),
		G('G',3,2), H('H', 2, 4), I('I', 9, 1), J('J', 1, 8), K('K', 1, 5), L('L', 4, 1), M('M', 2, 3),
		N('N', 6, 1), O('O', 8, 1),P('P', 2, 3), Q('Q', 1, 10), R('R', 6, 1), S('S', 4, 1), T('T', 6, 1),
		U('U', 4, 1), V('V', 2, 4),W('W', 2, 4), X('X', 1, 8), Y('Y', 2, 4), Z('Z', 1, 10);
		//@formatter: on
		
		private char	letter;
		private int		count;
		private int		value;
		
		
		Block(char l, int c, int v)
		{
			this.letter = l;
			this.count = c;
			this.value = v;
		}
		
		
		public char letter()
		{
			return this.letter();
		}
		
		
		public int count()
		{
			return this.count;
		}
		
		
		public int value()
		{
			return this.value;
		}
	}
}
