# picture

namespace WindowsFormsApp1
{
	public partial class Form1 : Form
	{
		private Graphics g;

		public Form1()
		{
			InitializeComponent();
			g = this.CreateGraphics();

			double x0 = 0.0;
			double x1 = 1.0;

			double step = 0.01;

			int height = 5;
			int width = 5;

			int scale = (int)((x1 - x0) / 0.01);

			Bitmap picture = new Bitmap(scale * height, scale * width);
			for (double d = x0; d < x1 * width; d += step){
				picture.SetPixel((int)(d * scale), (int)(Math.Sin(d) * scale) + 200, Color.Black);
			}
			PictureBox pbGraph = this.pbGraph;
			pbGraph.BackgroundImage = picture;
		}

		private void Form1_MouseClick(object sender, MouseEventArgs e)
		{
			Pen pen = new Pen(Color.SlateBlue);
			SolidBrush solid = new SolidBrush(Color.Red);
			g.FillEllipse(solid, e.X, e.Y, 5, 5);
			g.DrawEllipse(pen, e.X, e.Y, 5, 5);

			solid.Dispose();
			pen.Dispose();

		}

		private void button1_Click(object sender, EventArgs e)
		{

		}

		private void btnScaleLess_Click(object sender, EventArgs e)
		{

		}
	}
