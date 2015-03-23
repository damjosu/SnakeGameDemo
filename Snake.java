import java.util.Random;
import java.util.ArrayList;
import java.util.HashSet;
public class Snake
{
    private boolean noMoreMoves;
    private Segment segment;    
    private ArrayList<Segment> segments;    
    public Snake(int initialSize, Canvas canvas)
    {
        int x;
        int y;
        noMoreMoves = false;
        segment = null;
        Random rnd = new Random();        
        segments = new ArrayList<>();        
        x = (canvas.getSize().width / 2);
        while (x % (segment.getSize() - 1) != 0) 
        {
            x--;
        }
        y = (canvas.getSize().height / 2);
        while (y % (segment.getSize() - 1) != 0) 
        {
            y--;
        }
        segment = new Segment(x, y, rnd.nextInt(4));
        segments.add(segment);
        segment.drawSegment(canvas);
        increaseSnakeSize((initialSize - 1), canvas);
    }
    
    public void increaseSnakeSize(int size, Canvas canvas)
    {
        int i = 0;
        int x;
        int y;
        int angle;
        Random rnd = new Random();
        HashSet<Integer> angles;
        while (!noMoreMoves && (i < size))
        {
            x = segments.get(segments.size() - 1).getXf();
            y = segments.get(segments.size() - 1).getYf();
            angles = new HashSet<>();
            do {
                angles.add(angle = rnd.nextInt(4)); //genera un numero aleatorio del 0 al 3 y lo añade al conjunto, si existe lo reemplaza.
                segment = new Segment(x, y, angle);
                System.out.println(snakeCollision(segment));
                System.out.println(segment.segmentInsideCanvas(canvas));
            } while ((angles.size() < 4) && (snakeCollision(segment) || !segment.segmentInsideCanvas(canvas)));
            if ((angles.size() == 4) && (snakeCollision(segment) || !segment.segmentInsideCanvas(canvas)))
            {
                noMoreMoves = true;
            }
            else
            {
                segments.add(segment);
                segment.drawSegment(canvas);
            }
            i++;
        }        
    }
    
    public boolean noMoreMoves()
    {
        return noMoreMoves;
    }
    
    private boolean snakeCollision(Segment segment)
    {
        int i = 0;
        boolean snakeCollision = false;
        while (!snakeCollision && (i < segments.size()))
        {
            if (segment.segmentCollision(segments.get(i)))
            {
                snakeCollision = true;
            }
            i++;
        }
        return snakeCollision;
    }
}
