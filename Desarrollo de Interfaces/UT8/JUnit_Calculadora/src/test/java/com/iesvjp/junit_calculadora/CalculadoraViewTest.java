package com.iesvjp.junit_calculadora;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculadoraViewTest {

    private FrameFixture window;

    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @Before
    public void setUp() {
        CalculadoraView frame = GuiActionRunner.execute(CalculadoraView::new);
        window = new FrameFixture(frame);
        window.show();
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }

    // a) Suma correcta
    @Test
    public void testSumaCorrecta() {
        window.textBox("num1").enterText("3");
        window.textBox("num2").enterText("5");
        window.button("sumar").click();

        assertThat(window.textBox("resultado").text()).isEqualTo("8");
    }

    // b) Campo resultado no editable
    @Test
    public void testResultadoNoEditable() {
        window.textBox("resultado").requireNotEditable();
    }

    // c) Campo vacío → debe mostrar un JOptionPane de error
    @Test
    public void testCampoVacioMuestraError() {
        window.textBox("num1").enterText("");
        window.textBox("num2").enterText("5");
        window.button("sumar").click();

        window.dialog().requireVisible();

        // Comprobar el título REAL del JOptionPane
        assertThat(window.dialog().target().getTitle()).isEqualTo("Error");
    }

}
